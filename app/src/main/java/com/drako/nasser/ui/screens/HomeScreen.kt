package com.drako.nasser.ui.screens

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Environment
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.app.ComponentActivity
import androidx.navigation.NavController
import com.drako.nasser.R
import com.drako.nasser.navigation.AppScreens
import com.drako.nasser.reader.ReadViewHTTPD
import com.drako.nasser.server.NanoServer
import com.drako.nasser.server.NetworkUtils
import com.drako.nasser.utils.FileReader
import com.drako.nasser.utils.FileUtils
import java.io.File


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(topBar = { Toolbar() }) { padding ->
        Box(
            Modifier.fillMaxSize()
                .padding(padding)
                .background(colorResource(R.color.amoled_black))
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
                    .background(
                        colorResource(R.color.amoled_black_inside),
                        RoundedCornerShape(25.dp)
                    )
            ) {
                item {
                    HomeBodyContent(navController)
                }
                item {
                    Spacer(modifier = Modifier.padding(8.dp))
                    NetworkIpList()
                }
            }

            ReadViewHTTPD()
            //FileList()

            //FileReader()

            //val directoryPath = Environment.getExternalStorageDirectory().absolutePath
            //Toast.makeText(LocalContext.current, directoryPath, Toast.LENGTH_SHORT).show()
        }
    }
}

@Composable()
fun HomeBodyContent(navController: NavController) {
    Column(
        Modifier.fillMaxSize().padding(16.dp),
        // verticalArrangement = Arrangement.Center,
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { navController.navigate(AppScreens.AboutScreen.route + "/Parameter") }) {
            Text(
                "About"
            )
        }
        CardHome("Home Screen")
        CardHome("""This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.
                       It also contains some basic inner content, such as this text.
                       You have pressed the floating action button 12 times.
                   """.trimIndent())


        Spacer(modifier = Modifier.padding(8.dp))

        var serverStatus: Boolean by rememberSaveable() { mutableStateOf(false) }

        Row(
            Modifier.fillMaxWidth()
                .background(
                    colorResource(R.color.box_card), shape = RoundedCornerShape(20.dp)
                ).padding(16.dp)
        ) {
            Text(
                modifier = Modifier.padding(8.dp),
                text =
                """
                       Server Status:
                   """.trimIndent()
            )
            Text(
                modifier = Modifier.padding(8.dp),
                color = if (serverStatus) Color.Green else Color.Red,
                text = if (serverStatus) "ON" else "OFF"
            )
        }

        CardHome("Server in Local")

        val nanoServer = NanoServer(LocalContext.current)
        Spacer(modifier = Modifier.padding(8.dp))
        Button(onClick = {
            if (serverStatus) {
                nanoServer.stopServer()
            } else {
                nanoServer.runServer()
            }
            serverStatus = !serverStatus
        }) {
            Text(text = if (serverStatus) "ACTIVATED" else "INACTIVE")
        }
        Button(onClick = {
            nanoServer.stopServer()
        }) {
            Text(text = "APAGAR")
        }
    }


}


@Composable
fun CardHome(text: String){
    Spacer(modifier = Modifier.padding(8.dp))
    Box(
        Modifier.fillMaxWidth()
            .background(
                color = colorResource(R.color.box_card),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(16.dp),
    ) {
        Text(
            modifier = Modifier.padding(8.dp),
            text =
            """
                       $text
                   """.trimIndent()
        )
    }
}



@Composable
fun NetworkIpList() {
    val context: Context = LocalContext.current
    /*Surface(modifier = Modifier.fillMaxWidth()) {
        val networkUtils = NetworkUtils(context)
        IPAddressesList(ipAddresses = networkUtils.getAllIPv4Addresses())
    }*/
    val networkUtils = NetworkUtils(context)
    IpListAdress(ipAddresses = networkUtils.getAllIPAddresses())
}


@Composable
fun IPAddressesList(ipAddresses: List<String>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth()
            .background(
                colorResource(R.color.amoled_black_inside),
                RoundedCornerShape(30.dp)
            )

    ) {
        items(ipAddresses) { ipAddress ->
            IPAddressItem(ipAddress = ipAddress)
        }
    }
}

@Composable
fun IpListAdress(ipAddresses: List<String>) {
    Column(
        Modifier.fillMaxWidth().padding(16.dp).background(colorResource(R.color.box_card),
            RoundedCornerShape(20.dp)),
    ) {

        Box(
            Modifier.fillMaxWidth()
                .background(
                    color = colorResource(R.color.box_card),
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(16.dp),
        ) {
            Column {
                ipAddresses.forEach { ipAddress ->
                    Text(
                        modifier = Modifier.padding(8.dp),
                        text =
                        """
                       $ipAddress
                   """.trimIndent()
                    )
                }
            }
        }
    }
}


@Composable
fun IPAddressItem(ipAddress: String) {
    Text(
        text = ipAddress,
        style = MaterialTheme.typography.bodySmall,
        color = MaterialTheme.colorScheme.onSurface
    )
}


@Composable
fun FileList() {
    val context: Context = LocalContext.current
    val fileUtils = FileUtils(context, "routexample")

    // Solicitar permisos en tiempo de ejecución
    LaunchedEffect(Unit) {
        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as ComponentActivity,
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                1
            )
        }
    }

    // Obtener la lista de archivos
    val files = fileUtils.getFilesInFolder()

    // Mostrar la lista de archivos
    LazyColumn {
        items(files) { file ->
            FileItem(file = file)
        }
    }
}

@Composable
fun FileItem(file: File) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.Red)
    ) {
        Icon(
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = file.name)
    }
}















