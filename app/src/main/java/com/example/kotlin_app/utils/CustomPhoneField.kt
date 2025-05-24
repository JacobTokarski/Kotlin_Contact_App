package com.example.kotlin_app.utils

// To będzie custom text field strikte pod ekran Home i ekran związany z numerem telefonu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlin_app.R
import com.example.kotlin_app.viewmodel.AuthState

@Composable
fun CustomPhoneField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Numer telefonu"
) {
    var phoneErrorMessage by remember { mutableStateOf<String?>(null) }

    val handleChange: (String) -> Unit = { input ->
        when {
            input.any { !it.isDigit() } -> {
                phoneErrorMessage = "Phone number can only contain numbers , please try again!."
            }
            input.length > 9 -> {
                phoneErrorMessage = "Wrong number format use only 9 numbers!"
            }

            else -> {
                phoneErrorMessage = null
                onValueChange(input)
            }
        }

        if (input.all { it.isDigit() } && input.length <= 9) {
            onValueChange(input)
        }
    }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = handleChange,
            label = { Text(label) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            isError = phoneErrorMessage != null,
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Colors.SecondPurple,
                unfocusedIndicatorColor = Colors.SecondPurple,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
            )
        )

        if (phoneErrorMessage != null) {
            Text(
                text = phoneErrorMessage!!,
                color = Color.Red,
                fontSize = 13.sp,
                modifier = Modifier.padding(start = 16.dp, top = 4.dp)
            )
        }
    }
}

