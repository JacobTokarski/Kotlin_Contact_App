package com.example.kotlin_app.view

import android.R.attr.top
import android.graphics.drawable.Icon
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
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.kotlin_app.utils.CustomTextField

@Composable
fun RegisterPage(navController: NavHostController){
    val interactionSource = remember { MutableInteractionSource() }
    var fullName by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Row(
            modifier = Modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = null // Usuwa poświatę takiego szarego kwadratu podczas naciśnięcia na back
                ) { navController.popBackStack() } // możliwość powrotu do poprzedniego ekranu
                .padding(start = 12.dp, top = 59.dp), //odległość całej struktury przycisku Back na ekranie Register
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "Back Icon",
                modifier = Modifier
                    .size(20.dp) //rozmiar strzałki
            )

            Text(
                text = "Back",
                color = Color(0xFF4A148C),
                fontSize = 12.sp //rozmiar tekstu back
            )
        }

        Image(
            painter = painterResource(id = R.drawable.elipse),
            contentDescription = "Top Right Image", //opis prawego zdjęcia
            modifier = Modifier
                .size(130.dp) //rozmiar prawego zdjęcia
                .padding(top = 10.dp) //odległość prawego zdjęcia od góry ekranu
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp), //padding na sztywno ustawiony dla kolumny
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
    ) {

        Spacer(modifier = Modifier.height(180.dp)) //odległość napisu Sign Up od góry ekranu

        Text(
            text = "Sign Up",
            fontSize = 30.sp, //rozmiar identyczny jak w przypadku przycisku Sign In - zgadza siee
            fontWeight = FontWeight.Bold, // !! Zmienić wartość na numeryczną opisaną w Figmie
            color = Color(0xFF4A148C), // !! Zmienic wartość owego koloru na hex z figmy , pocztać jak to zrobić
            textAlign = TextAlign.Left,
            modifier = Modifier
                .fillMaxWidth()

        )

        Spacer(modifier = Modifier.height(46.dp))

        CustomTextField( // !! Owy TextField wymagać będzie poprawek , musimy zrobić customego textfielda dla passworda oraz e-maila
            value = fullName,
            onValueChange = {fullName = it },
            placeholder = "Full Name",
            leadingIcon = Icons.Default.Person
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField( // !! Owy TextField wymagać będzie poprawek , musimy zrobić customego textfielda dla passworda oraz e-maila
            value = "",
            onValueChange = {},
            placeholder = { Text("Email") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = "User Icon") },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField( // !! Owy TextField wymagać będzie poprawek , musimy zrobić customego textfielda dla passworda oraz e-maila
            value = "",
            onValueChange = {},
            placeholder = { Text("Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "User Icon") },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(40.dp))

        OutlinedTextField( // !! Owy TextField wymagać będzie poprawek , musimy zrobić customego textfielda dla passworda oraz e-maila
            value = "",
            onValueChange = {},
            placeholder = { Text("Confirm Password") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = "User Icon") },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(12.dp)
        )

        Spacer(modifier = Modifier.height(80.dp))

        Button(
            onClick = { navController.navigate("login_page") },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(Color(0xFF9C27B0))
        ) {
            Text(text = "Sign Up", color = Color.White)
        }

        Spacer(modifier = Modifier.height(100.dp))

        Row {
            Text(
                text = "Already have an account?",
                fontSize = 15.sp,
                color = Color.Blue
        )
            Spacer(modifier = Modifier.padding(start = 5.dp))

            Text(
                text = "Sign In",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue,
                modifier = Modifier
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) { navController.navigate("login_page") }
            )
        }
    }
}



