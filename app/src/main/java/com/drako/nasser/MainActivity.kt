package com.drako.nasser

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
            helloApp()
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

@Preview(showBackground = true)
@Composable
fun helloApp(){
    NasserTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Greeting("drakolin")

        }
    }
}


@Preview(showBackground = true)
@Composable
fun ExampleModifier(){
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


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NasserTheme {
        Greeting("Master dev")
    }
}

@Preview(showBackground = true, name = "Test text")
@Composable
fun SecondPreview() {
    NasserTheme {
        Greeting("Master dev")
    }
}