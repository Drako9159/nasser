package com.drako.nasser

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.drako.nasser.navigation.AppNavigation
import com.drako.nasser.server.NanoServer
import com.drako.nasser.ui.theme.NasserTheme
import fi.iki.elonen.NanoHTTPD

class MainActivity : ComponentActivity() {

    private var server: NanoServer? = null

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        initUI()
    }


    private fun initUI() {

        setContent {


            //helloApp()
            //Content()
            //ViewContainer()
            /*
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colorScheme.background
                        ) {
                            LoginScreen(LoginViewModel())

                        }*/




            //LayoutApp()
            AppNavigation()
        }
        runServer()
    }

    override fun onDestroy() {
        super.onDestroy()
        server?.stop()
    }

    private fun runServer() {
        server = NanoServer()
        Thread {
            try {
                server?.start(NanoHTTPD.SOCKET_READ_TIMEOUT, false)
                runOnUiThread {
                    println("Server is running now")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }.start()
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun ViewContainer() {
    Scaffold(
        topBar = { Toolbar() },
        content = { Content() },
        //floatingActionButton = { FAB() },
        //floatingActionButtonPosition = FabPosition.End
    )

}

@Composable
fun FAB() {
    val context = LocalContext.current
    FloatingActionButton(onClick = {
        Toast.makeText(context, "hellow", Toast.LENGTH_SHORT).show()
    }) {
        Text("X")
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar() {
    TopAppBar(
        title = { Text(text = "Nasser") },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = colorResource(id = R.color.background),
            titleContentColor = colorResource(id = R.color.white)
        )
    )
}

@Composable
fun Content() {

    Surface(modifier = Modifier.fillMaxWidth(), color = MaterialTheme.colorScheme.background) {
        var show by rememberSaveable { mutableStateOf(false) }
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Button(onClick = { show = true }) {
                Text(text = "Dialog")
            }
        }
        MyDialog(show, { show = false }, { Log.i("drako", "clicked") })
    }

}


@Composable
fun MyDialog(show: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit) {
    if (show) {
        AlertDialog(onDismissRequest = { onDismiss() },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "CONFIRM")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "DECLINE")
                }
            },
            title = { Text(text = "Message") },
            text = { Text(text = "Message content") }
        )

    }


}

@Composable
fun ContentTest() {
    //var counter by remember { mutableStateOf(0) }
    var counter by rememberSaveable { mutableStateOf(0) }
    //LazyColumn as RecylerView
    LazyColumn(modifier = Modifier.fillMaxSize().background(Color.Red).padding(16.dp)) {
        item {
            Image(
                modifier = Modifier.fillMaxWidth().height(400.dp),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "reference"
            )
            Row(modifier = Modifier.padding(top = 8.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.ic_favorite),
                    contentDescription = "icon favorite",
                    modifier = Modifier.clickable { counter++ }

                )
                Text(
                    text = counter.toString(),
                    color = Color.White,
                    modifier = Modifier.padding(start = 4.dp)
                )
            }
            Text(
                text = "Title",
                fontSize = 32.sp,
                color = Color.White,
                modifier = Modifier.fillMaxWidth(),

                textAlign = TextAlign.Center

            )

            Text(
                text = "text for column row 2",
                color = Color.White,
                modifier = Modifier.padding(top = 0.dp)
            )
            Text(
                text = "text for column row 3",
                color = Color.White,
                modifier = Modifier.padding(top = 0.dp)
            )
            Text(
                text = "text for column row 4",
                color = Color.White,
                modifier = Modifier.padding(top = 0.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                item {
                    Text(text = "Stack", color = Color.White)
                    Text(text = "Java", color = Color.White)
                    Text(text = "Kotlin", color = Color.White)
                }
            }
        }
    }

}


@Composable
fun helloApp() {
    NasserTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Greeting("drakolin")

        }
    }
}


//@Preview(showBackground = true)
@Composable
fun ExampleModifier() {
    Text(text = "alternative", modifier = Modifier.padding(16.dp))
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        color = Color.Blue,
        modifier = modifier
    )

}


//@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NasserTheme {
        Greeting("Master dev")
    }
}

//@Preview(showBackground = true, name = "Test text")
@Composable
fun SecondPreview() {
    NasserTheme {
        Greeting("Master dev")
    }
}