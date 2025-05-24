package com.example.kotlin_app.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kotlin_app.R
import com.example.kotlin_app.utils.Colors
import com.example.kotlin_app.utils.CustomPasswordField
import com.example.kotlin_app.utils.CustomTextField
import com.example.kotlin_app.utils.Routes
import com.example.kotlin_app.viewmodel.AuthState
import com.example.kotlin_app.viewmodel.AuthViewModel
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun RegisterPage(modifier: Modifier, navController: NavHostController, authViewModel: AuthViewModel) {
    val interactionSource = remember { MutableInteractionSource() }
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirm_password by remember { mutableStateOf("") }

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    val systemUiController = rememberSystemUiController()
    val useDarkIcons = true

    LaunchedEffect(authState.value) {
        when(authState.value){
            is AuthState.Authenticated -> navController.navigate(Routes.homepage)
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message, Toast.LENGTH_LONG).show()
            else -> Unit
        }
    }

    SideEffect {
        systemUiController.setSystemBarsColor(
            color = Color.White,
            darkIcons = useDarkIcons
        )
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { navController.popBackStack() }
                    .padding(
                        start = 12.dp,
                        top = 59.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "Back Icon",
                    modifier = Modifier
                        .size(20.dp)
                )

                Text(
                    text = "Back",
                    color = Colors.PrimaryPurple,
                    fontSize = 12.sp,
                    fontWeight = FontWeight(400)
                )
            }

            Image(
                painter = painterResource(id = R.drawable.elipse),
                contentDescription = "Top Right Image",
                modifier = Modifier
                    .size(130.dp)
                    .padding(top = 10.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
        ) {

            Spacer(modifier = Modifier.height(180.dp))

            Text(
                text = "Sign Up",
                fontSize = 30.sp,
                fontWeight = FontWeight(700),
                color = Colors.PrimaryPurple,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .fillMaxWidth()

            )

            Spacer(modifier = Modifier.height(46.dp))

            CustomTextField(
                value = fullName,
                onValueChange = { fullName = it },
                placeholder = "Full Name",
                leadingIcon = Icons.Default.Person
            )

            Spacer(modifier = Modifier.height(40.dp))

            CustomTextField(
                value = email,
                onValueChange = { email = it },
                placeholder = "Email",
                leadingIcon = Icons.Default.Email,
            )

            Spacer(modifier = Modifier.height(40.dp))

            CustomPasswordField(
                value = password,
                onValueChange = { password = it },
                placeholder = "Password",
                leadingIcon = Icons.Default.Lock,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(40.dp))

            CustomPasswordField(
                value = confirm_password,
                onValueChange = { confirm_password = it },
                placeholder = "Confirm Password",
                leadingIcon = Icons.Default.Lock,
                isPassword = true
            )

            Spacer(modifier = Modifier.height(80.dp))

            Button(
                onClick = {
                    authViewModel.signup(email, password)
                },
                enabled = authState.value != AuthState.Loading,
                modifier = Modifier
                    .width(390.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(Colors.ThirdPurple)
            ) {
                Text(text = "Sign Up", color = Color.White)
            }

            Spacer(modifier = Modifier.height(100.dp)) // wartość ta może ulec zmianie w zależności od formatowania

            Row {
                Text(
                    text = "Already have an account?",
                    fontSize = 15.sp,
                    color = Colors.PrimaryPurple
                )
                Spacer(modifier = Modifier.padding(start = 5.dp))

                Text(
                    text = "Sign In",
                    fontSize = 15.sp,
                    fontWeight = FontWeight(700),
                    color = Colors.PrimaryPurple,
                    modifier = Modifier
                        .clickable(
                            interactionSource = interactionSource,
                            indication = null
                        ) { navController.navigate("login_page") }
                )
            }
        }
    }
}



