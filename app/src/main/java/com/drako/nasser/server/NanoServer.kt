package com.drako.nasser.server

import fi.iki.elonen.NanoHTTPD

class NanoServer : NanoHTTPD(8080) {

    //private var server: NanoHTTPD? = null
    override fun serve(session: NanoHTTPD.IHTTPSession): Response {
        val uri = session.uri;
        val responseText = "[server] is running: $uri"
        return newFixedLengthResponse(responseText);
    }

    fun runServer(server: NanoServer?) {
        //server = NanoServer()
        Thread {
            try {
                server?.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
        //server?.stop()
    }


    fun stopServer(server: NanoServer?) {
        try {
            server?.stop()
            super.stop()
            server?.closeAllConnections()
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
/*fun main() {
    val server = NanoServer();
    server.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false)
    println("[server] is running in port 8080")
}*/

