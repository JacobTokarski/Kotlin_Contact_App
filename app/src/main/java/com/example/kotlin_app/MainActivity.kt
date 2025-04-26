package com.example.kotlin_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlin_app.ui.theme.TodoAppTheme
import com.example.kotlin_app.home.TodoListPage
import com.example.kotlin_app.viewmodel.TodoViewModel
import com.example.kotlin_app.view.LoginPage
import com.example.kotlin_app.view.RegisterPage

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val todoViewModel = ViewModelProvider(this)[TodoViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            TodoAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "login_page") {
                        composable("login_page") {
                            LoginPage(navController)
                        }
                        composable("register_page") {
                            RegisterPage(navController)
                        }
                        composable("home_page") {
                            TodoListPage(todoViewModel)
                        }
                    }
                }
            }
        }
    }
}
