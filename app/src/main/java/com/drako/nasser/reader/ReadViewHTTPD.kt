package com.drako.nasser.reader

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.app.ComponentActivity
import androidx.core.content.ContextCompat
import java.io.File

@Composable
fun ReadViewHTTPD(): List<String> {
    val context = LocalContext.current
    var fileList by remember { mutableStateOf<List<String>>(emptyList()) }

    LaunchedEffect(key1 = Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Solicitar permisos en tiempo de ejecución
            ActivityCompat.requestPermissions(
                context as ComponentActivity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        } else {

            fileList = readViewHTTPD(context)

        }
    }
    return fileList
}


fun readViewHTTPD(context: Context): List<String> {
    val directoryPath = context.getExternalFilesDir(null)?.absolutePath + "/static"
    val directory = File(directoryPath)
    val fileList = mutableListOf<String>()

    try {
        // Crear la carpeta si no existe
        if (!directory.exists()) {
            val created = directory.mkdirs()
            if (!created) {
                Log.e("FolderCreation", "No se pudo crear la carpeta.")
            }
        }

        if (directory.exists() && directory.isDirectory) {
            val files = directory.listFiles()
            if (files != null) {
                for (file in files) {
                    fileList.add(file.name)
                }
            }
        } else {
            Log.e("FolderCreation", "La carpeta no existe o no es un directorio")
        }
    } catch (e: Exception) {
        Log.e("FileReader", "Error al leer archivos: ${e.message}")
    }

    return fileList
}

