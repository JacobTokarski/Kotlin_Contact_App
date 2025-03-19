package com.example.kotlin_app.view

import android.R.attr.top
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.kotlin_app.R

@Composable
fun RegisterPage(navController: NavHostController){
    val interactionSource = remember { MutableInteractionSource() }

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
                .size(150.dp) //rozmiar prawego zdjęcia
                .padding(top = 5.dp) //odległość prawego zdjęcia od góry ekranu
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {  }
}



