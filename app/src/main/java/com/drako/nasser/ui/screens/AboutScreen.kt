package com.drako.nasser.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.drako.nasser.R


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController, text: String?) {
    Scaffold(topBar = { ToolbarWithBack(navController) }) { padding ->
        Box(
            Modifier.fillMaxSize()
                .padding(padding)
                .background(colorResource(R.color.amoled_black))
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth()
                    .background(
                        colorResource(R.color.amoled_black_inside),
                        RoundedCornerShape(30.dp)
                    )
            ) {
                item {
                    AboutBodyContent(navController, text)
                }
            }
        }
    }
}

@Composable()
fun AboutBodyContent(navController: NavController, text: String?) {
    Column(
        Modifier.fillMaxSize().padding(16.dp),
        //verticalArrangement = Arrangement.Center,
        //horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*Text("About Navigation")
        text?.let {
            Text(it)
        }*/
        //Button(onClick = { navController.navigate(AppScreens.HomeScreen.route) }) { Text("Navegar") }

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
                       About Screen
                   """.trimIndent()
            )
        }

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
                    Esta aplicación está siendo desarrollada y mantenida por Antoio.jar 
                    está pensada para ofrecer un servicio tipo NAS y que los usuarios puedan tener accesso a sus archivos en una red local de manera efícaz 
                   """.trimIndent()
            )
        }
    }
}

