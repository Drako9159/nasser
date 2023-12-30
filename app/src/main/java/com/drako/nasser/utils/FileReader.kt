package com.drako.nasser.utils

import android.content.ContentUris
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.ComponentActivity
import androidx.core.content.ContextCompat
import coil.compose.rememberImagePainter
import java.io.File

@Composable
fun FileReader() {
    val context = LocalContext.current
    var fileList by remember { mutableStateOf<List<String>>(emptyList()) }
    var imageList by remember { mutableStateOf<List<Uri>>(emptyList()) }

    LaunchedEffect(key1 = Unit) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M &&
            ContextCompat.checkSelfPermission(
                context,
                android.Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // Solicitar permisos en tiempo de ejecución
            ActivityCompat.requestPermissions(
                context as ComponentActivity,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        } else {
            // Si se tienen los permisos, leer los archivos
            fileList = readFilesFromDirectory(context)
            imageList = readImagesFromGallery(context)
            //fileList = readImagesFromGallery(context)
        }
    }

    Column {
        LazyColumn {
            /*items(fileList) { file ->
                ClickableText(
                    text = AnnotatedString(file),
                    onClick = {}
                )
            }*/

            items(imageList) {imageUri ->
                Image(
                    painter = rememberImagePainter(imageUri),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .padding(4.dp)
                )
            }
        }
    }
}

fun readImagesFromGallery(context: Context): List<Uri> {
    val imageList = mutableListOf<Uri>()
    try {
        val projection = arrayOf(
            MediaStore.Images.Media._ID,
            MediaStore.Images.Media.DISPLAY_NAME
        )
        val query = context.contentResolver.query(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            projection,
            null,
            null,
            "${MediaStore.Images.Media.DATE_ADDED} DESC"
        )
        query?.use { cursor ->
            val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
            val nameColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)

            while (cursor.moveToNext()) {
                val id = cursor.getLong(idColumn)
                val name = cursor.getString(nameColumn)
                val contentUri = ContentUris.withAppendedId(
                    MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id
                )
                imageList.add(contentUri)
            }
        }
    } catch (e: Exception) {
        Log.e("GalleryReader", "Error in reader: ${e.message}")
    }
    return imageList


}

fun readFilesFromDirectory(context: Context): List<String> {
    //val directoryPath = Environment.getExternalStorageDirectory().absolutePath + "/manager"
    val directoryPath = context.getExternalFilesDir(null)?.absolutePath + "/manager"
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








