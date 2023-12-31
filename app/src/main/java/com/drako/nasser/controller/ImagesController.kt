package com.drako.nasser.controller

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import android.webkit.MimeTypeMap
import com.drako.nasser.reader.getImages
import fi.iki.elonen.NanoHTTPD.IHTTPSession
import fi.iki.elonen.NanoHTTPD.Response
import fi.iki.elonen.NanoHTTPD.newFixedLengthResponse
import org.json.JSONObject
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException


/**
 *
 * TODO
 * Crear una función para mandar los archivos de las imagenes
 * Crea una función para descargar la imagen
 */

fun ImagesController(session: IHTTPSession, context: Context): Response {
    var listImages = getImages(context)
    if (listImages.isNotEmpty()) {

        val imageInfo = listImages[67]
        val imageUri = imageInfo.first
        val fileName = imageInfo.second
        val imageFile: File? = imageUri.toFile(context)

        if (imageFile != null) {

            val mimeType = getMimeType(context, imageUri)

            val nanoResponse = newFixedLengthResponse(
                Response.Status.OK,
                mimeType,
                FileInputStream(imageFile),
                imageFile?.length()!!.toLong()
            )

            return newFixedLengthResponse(
                Response.Status.NOT_FOUND,
                "text/plain",
                imageUri.toString()
            )


            // Agregar encabezado para la descarga del archivo (puedes ajustar según tus necesidades)
            nanoResponse.addHeader("Content-Disposition", "attachment; filename='${fileName}'")

            return nanoResponse
        } else {
            return newFixedLengthResponse(
                Response.Status.NOT_FOUND,
                "text/plain",
                "No se pudo obtener el archivo"
            )
        }


    } else {
        // Enviar una respuesta de error si no hay imágenes
        return newFixedLengthResponse(
            Response.Status.NOT_FOUND,
            "text/plain",
            "No se encontraron imágenes"
        )
    }
}

fun getMimeType(context: Context, uri: Uri): String {
    var mimeType =
        "application/octet-stream" // Valor predeterminado si no se puede determinar el tipo MIME

    try {
        val contentResolver: ContentResolver = context.contentResolver
        val cursor = contentResolver.query(uri, null, null, null, null)
        cursor?.use {
            if (it.moveToFirst()) {
                val displayNameIndex = it.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME)
                if (displayNameIndex != -1) {
                    val displayName = it.getString(displayNameIndex)
                    val extension = MimeTypeMap.getFileExtensionFromUrl(displayName)
                    if (extension != null) {
                        mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
                            ?: mimeType
                    }
                }
            }
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }

    return mimeType
}


fun Uri.toFile(context: Context): File? {
    val contentResolver = context.contentResolver
    val file = File(context.cacheDir, this.lastPathSegment ?: "image.jpg")
    try {
        contentResolver.openInputStream(this)?.use { inputStream ->
            FileOutputStream(file).use { outputStream ->
                inputStream.copyTo(outputStream)
            }
        }
        return file
    } catch (e: IOException) {
        e.printStackTrace()
    }
    return null
}


fun Map<String, Any?>.toJsonObject(): JSONObject {
    val jsonObject = JSONObject()
    for ((key, value) in this) {
        jsonObject.put(key, value)
    }
    return jsonObject
}