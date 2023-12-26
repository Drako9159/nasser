package com.drako.nasser.ui.view


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.drako.nasser.R
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun HomeScreen() {
    SetStatusBarColor(color = colorResource(id = R.color.amoled_black))
    Scaffold(
        topBar = { Toolbar() }
    ) {
        innerPadding ->
        Column(modifier = Modifier.padding(innerPadding), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(
                modifier = Modifier.padding(8.dp),
                text =
                """
                    This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.

                    It also contains some basic inner content, such as this text.

                    You have pressed the floating action button 12 times.
                """.trimIndent(),
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Toolbar() {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.app_name_toolbar)) },
        colors = TopAppBarDefaults.largeTopAppBarColors(
            containerColor = colorResource(id = R.color.amoled_black),
            titleContentColor = colorResource(id = R.color.white)
        )
    )
}


@Composable
fun Content() {
    /*
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
        Box(
            modifier = Modifier.fillMaxWidth().background(color = Color.Blue),
            contentAlignment = Alignment.Center
        ) {
            //Text(text = "HelloWorld")
            Box(
                modifier = Modifier.fillMaxWidth()
                    .clip(shape = RoundedCornerShape(20.dp))
                    .background(color = Color.Red)
                    .border(BorderStroke(2.dp, SolidColor(Color.Red)))
                    .padding(16.dp)
            ) {
                Text(text = "Server status is off")
            }

        }

    }*/

    Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            modifier = Modifier.padding(8.dp),
            text =
            """
                    This is an example of a scaffold. It uses the Scaffold composable's parameters to create a screen with a simple top app bar, bottom app bar, and floating action button.

                    It also contains some basic inner content, such as this text.

                    You have pressed the floating action button 12 times.
                """.trimIndent(),
        )
    }




}

@Composable
fun SetStatusBarColor(color: Color) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setSystemBarsColor(color)
    }
}

