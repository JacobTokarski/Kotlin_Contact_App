package com.example.kotlin_app.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kotlin_app.R



@Composable
fun LoginPage(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp), //?
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

        Text(
            text = "Sign in",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold, // !! Zmienić wartość na numeryczną opisaną w Figmie
            color = Color(0xFF4A148C), // !! Zmienic wartość owego koloru na hex z figmy , pocztać jak to zrobić
            textAlign = TextAlign.Left,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(46.dp))

        OutlinedTextField( // !! Owy TextField wymagać będzie poprawek , musimy zrobić customego textfielda dla passworda oraz e-maila
            value = "",
            onValueChange = {},
            placeholder = { Text("Email or User Name") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = "User Icon") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField( // !! Owy TextField wymagać będzie poprawek , musimy zrobić customego textfielda dla passworda oraz e-maila
            value = "",
            onValueChange = {},
            placeholder = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "Lock Icon") },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Forget Password?",
            color = Color(0xFF4A148C),
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.End)
        )

        Spacer(modifier = Modifier.height(40.dp))

        Button(
            onClick = { navController.navigate("register_page") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF9C27B0))
        ) {
            Text(text = "Sign in", color = Color.White)
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "Don’t have an account? Sign Up",
            fontSize = 14.sp,
            color = Color.Blue
        )
    }
}
