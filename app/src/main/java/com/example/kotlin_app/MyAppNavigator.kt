package com.example.kotlin_app

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlin_app.home.HomePage
import com.example.kotlin_app.utils.Routes
import com.example.kotlin_app.view.LoginPage
import com.example.kotlin_app.view.RegisterPage
import com.example.kotlin_app.viewmodel.AuthViewModel
import com.example.kotlin_app.viewmodel.HomeViewModel

@Composable
fun MyAppNavigation(modifier: Modifier = Modifier, authViewModel: AuthViewModel, viewModel: HomeViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.loginPage, builder = {
        composable(Routes.loginPage){
            LoginPage(modifier, navController, authViewModel)
        }
        composable(Routes.registerPage) {
            RegisterPage(modifier, navController, authViewModel)
        }
        composable(Routes.homepage){
            HomePage(modifier, navController, authViewModel , viewModel )
        }
    })
}