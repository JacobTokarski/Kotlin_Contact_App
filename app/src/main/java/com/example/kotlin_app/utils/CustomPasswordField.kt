package com.example.kotlin_app.utils

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// w tym pliku znajdować się będzie customowy button haseł w ekranie logowania oraz rejestracji

@Composable
fun CustomPasswordField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: Int,
    isPassword: Boolean = false
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                color = Color.Gray,
                fontSize = 16.sp
            )
        },
        leadingIcon = {
            Icon(
                painter = painterResource(id = leadingIcon),
                contentDescription = "Input Icon",
                tint = Color(0xFF7B1FA2) // Fioletowy kolor dla ikony
            )
        },
        visualTransformation = if (isPassword) VisualTransformation.None else VisualTransformation.None,
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp), // ✅ Rounded corners
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color(0xFF7B1FA2), // ✅ Purple border when focused
            unfocusedIndicatorColor = Color(0xFF7B1FA2) // ✅ Purple border when not focused
        )
    )
}