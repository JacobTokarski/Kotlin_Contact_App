package com.example.kotlin_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.kotlin_app.ui.theme.TodoAppTheme
import com.example.kotlin_app.home.HomePage
import com.example.kotlin_app.viewmodel.HomeViewModel
import com.example.kotlin_app.view.LoginPage
import com.example.kotlin_app.view.RegisterPage
import com.example.kotlin_app.viewmodel.AuthViewModel
import kotlin.getValue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val homeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        enableEdgeToEdge()
        val authViewModel: AuthViewModel by viewModels()
        val viewModel: HomeViewModel by viewModels()
        val modifier = Modifier.fillMaxSize()
        setContent {
            TodoAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyAppNavigation(
//                        modifier = Modifier.padding(innerPadding),
                        authViewModel = authViewModel,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}

//                    val navController = rememberNavController()
//                    NavHost(navController = navController, startDestination = "login_page") {
//                        composable("login_page") {
//                            LoginPage(modifier, navController, authViewModel)
//                        }
//                        composable("register_page") {
//                            RegisterPage(modifier, navController, authViewModel)
//                        }
//                        composable("home_page") {
//                            HomePage(modifier, navController, homeViewModel)
//                        }
//                    }
//                }
//            }
//        }
//    }
//}






