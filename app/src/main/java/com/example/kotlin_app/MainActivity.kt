package com.example.kotlin_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.kotlin_app.ui.theme.TodoAppTheme
import com.example.kotlin_app.viewmodel.HomeViewModel
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
                        authViewModel = authViewModel,
                        viewModel = viewModel
                    )
                }
            }
        }
    }
}







