package com.example.kotlin_app.view

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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


@Composable
fun LoginPage(modifier: Modifier, navController: NavHostController, authViewModel: AuthViewModel) {

    val authState = authViewModel.authState.observeAsState()
    val context = LocalContext.current

    val interactionSource = remember { MutableInteractionSource() }
    var email_or_username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Authenticated -> navController.navigate(Routes.homepage) {
                popUpTo(0)
            }
            is AuthState.Error -> Toast.makeText(
                context,
                (authState.value as AuthState.Error).message,
                Toast.LENGTH_LONG
            ).show()

            else -> Unit
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(modifier = Modifier.height(62.dp))

        Image(
            painter = painterResource(id = R.drawable.logo_1),
            contentDescription = "Main Login_View_Picture",
            modifier = Modifier
                .size(129.dp)
        )

        Spacer(modifier = Modifier.height(21.dp))


        Spacer(modifier = Modifier.height(46.dp))

        CustomTextField(
            value = email_or_username,
            onValueChange = {email_or_username = it },
            placeholder = "Email or User Name",
            leadingIcon = Icons.Default.Person,
        )

        Spacer(modifier = Modifier.height(40.dp))

        CustomPasswordField(
            value = password,
            onValueChange = {password = it},
            placeholder = "Password",
            leadingIcon = Icons.Default.Lock,
            isPassword = true
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Forget Password?",
            color = Colors.PrimaryPurple,
            fontSize = 15.sp,
            fontWeight = FontWeight(700),
            modifier = Modifier.align(Alignment.End)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = {
                authViewModel.login(email_or_username, password)
                      }, enabled = authState.value != AuthState.Loading,
            modifier = Modifier
                .width(390.dp)
                .height(50.dp),
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(Colors.ThirdPurple)
        ) {
            Text(text = "Sign in", color = Color.White)
        }

        Spacer(modifier = Modifier.height(250.dp))

        Row {
            Text(
                text = "Dont have an account?",
                fontSize = 15.sp,
                color = Colors.PrimaryPurple,
                fontWeight = FontWeight(400)
            )
            Spacer(modifier = Modifier.padding(start = 5.dp))

            Text(
                text = "Sign Up",
                fontSize = 15.sp,
                fontWeight = FontWeight(700),
                color = Colors.PrimaryPurple,
                modifier = Modifier
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { navController.navigate("register_page") }
            )
        }
    }
}



