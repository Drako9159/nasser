package com.drako.nasser.ui.screens


import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.drako.nasser.R
import com.drako.nasser.navigation.AppScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {

    val alpha = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true) {
        alpha.animateTo(1f, animationSpec = tween(1500))
        delay(2000)
        navController.popBackStack()
        navController.navigate(AppScreens.HomeScreen.route)
    }
    Column(
        modifier = Modifier.fillMaxSize().background(colorResource(R.color.amoled_black)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Image(
            painter = painterResource(id = R.drawable.ic_splash),
            contentDescription = "Nasser Icon",
            Modifier.alpha(alpha.value).size(150.dp, 150.dp)
        )

        Text(
            "NASSER",
            fontSize = 30.sp,
            color = Color.White,
            modifier = Modifier.alpha(alpha.value)

        )


    }
}
