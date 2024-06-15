package com.example.firebaseauthentification.ui.theme.navigation

sealed class Screens(val route: String) {
    object LogInScreen: Screens(route = "LogIn_Screen")
    object SingUpScreen: Screens(route = "SignUp_Screen")
    object HomeScreens: Screens(route = "Home_Screens"){
        object Podcast: Screens(route = "Podcast")
    }
}