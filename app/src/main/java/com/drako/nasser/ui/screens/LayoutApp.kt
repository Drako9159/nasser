package com.drako.nasser.ui.screens

import android.annotation.SuppressLint
import android.widget.Toolbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.drako.nasser.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun LayoutApp(toolbar: Toolbar) {
    SetStatusBarColor(color = colorResource(id = R.color.amoled_black))
    Scaffold(topBar = { toolbar }) { padding ->
        Box(
            Modifier.fillMaxSize()
                .padding(padding)
                .background(colorResource(R.color.amoled_black))
        ) {
            Column(
                Modifier.fillMaxSize()
                    .background(
                        colorResource(R.color.amoled_black_inside),
                        RoundedCornerShape(30.dp)
                    ).padding(16.dp)
            ) {  }
        }
    }

}


@Composable
fun Content(padding: PaddingValues) {
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
    Box(Modifier.fillMaxSize().padding(padding).background(colorResource(R.color.amoled_black))) {
        Column(
            Modifier.fillMaxSize()
                .background(colorResource(R.color.amoled_black_inside), RoundedCornerShape(30.dp))
                .padding(16.dp)
        ) {

            //HomeScreen()

        }
    }


}


