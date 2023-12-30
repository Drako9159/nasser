package com.drako.nasser.server

import android.content.Context
import fi.iki.elonen.NanoHTTPD
import java.io.File
import java.io.IOException
import java.io.InputStream

class NanoServer(private val context: Context) : NanoHTTPD(8080) {

    private var server: NanoHTTPD? = null
    override fun serve(session: IHTTPSession): Response {

        val uri = session.uri;
        if (uri.contains("/api")) {
        }
        if (uri.equals("/")) {
            return parseResposne(serveStaticFiles("/index.html"))
        }

        return parseResposne(serveStaticFiles(uri))
    }

    fun runServer() {
        Thread {
            try {
                server = NanoServer(context)
                server?.start(SOCKET_READ_TIMEOUT, false)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }


    fun stopServer() {
        try {
            server?.stop()
            server?.closeAllConnections()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }


    fun parseResposne(res: Response): Response {
        res.addHeader("Access-Control-Allow-Origin", "*");
        res.addHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
        res.addHeader(
            "Access-Control-Allow-Headers",
            "Origin, X-Requested-With, Content-Type, Accept"
        );
        return res;
    }

    fun serveStaticFiles(filePath: String): Response {
        return try {
            val baseDirectory = context.getExternalFilesDir(null)?.absolutePath
            if (baseDirectory != null) {
                val fullPath = "$baseDirectory/static$filePath"
                val inputStream: InputStream? = File(fullPath).inputStream()
                if (inputStream != null) {
                    val mimeType = getMimeTypeForFile(filePath)
                    newFixedLengthResponse(
                        Response.Status.OK,
                        mimeType,
                        inputStream,
                        inputStream.available().toLong()
                    )
                } else {
                    newFixedLengthResponse(
                        Response.Status.NOT_FOUND,
                        "text/plain",
                        "File not found"
                    )
                }
            } else {
                newFixedLengthResponse(
                    Response.Status.INTERNAL_ERROR,
                    "text/plain",
                    "Error in get files"
                )
            }
        } catch (e: IOException) {
            e.printStackTrace()
            newFixedLengthResponse(
                Response.Status.INTERNAL_ERROR,
                "text/plain",
                "Route Not Found"
            )
        }
    }
}


