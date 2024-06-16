package com.example.firebaseauthentification.view.theme.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

import androidx.navigation.compose.rememberNavController
import com.example.firebaseauthentification.view.HomeScreens
import com.example.firebaseauthentification.use_case.login.login.LogInScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screens.LogInScreen.route) {
        composable(Screens.LogInScreen.route) {
            LogInScreen(navController) }
        composable(Screens.SingUpScreen.route) {
            HomeScreens(navController) }

    }
}
