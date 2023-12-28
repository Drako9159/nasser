package com.drako.nasser.navigation

sealed class AppScreens(val route: String) {

    object HomeScreen : AppScreens("home_screen")
    object AboutScreen : AppScreens("about_screen")

}
