package com.drako.nasser.utils

import android.content.Context
import java.io.File

class FileUtils(private val context: Context, private val folderName: String) {
    fun getFilesInFolder(): List<File> {
        val folder = File(context.getExternalFilesDir(null), folderName)
        if (!folder.exists() || !folder.isDirectory) {
            if (!folder.mkdirs()) {
                throw IllegalStateException("No se pudo crear la carpeta.")
            }
        }
        return folder.listFiles()?.toList() ?: emptyList()
    }
}




