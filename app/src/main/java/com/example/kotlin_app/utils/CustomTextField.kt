package com.example.kotlin_app.utils

// Tu będą custom textfiledy pod hasło oraz e-mail

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.kotlin_app.R

@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    leadingIcon: ImageVector? = null, // Taki warunek pozwala dodać nową ikonę albo w ogóle jej nie dodawać
//    iconTint: Color = Color(0xFF7B1FA2), // Jest to kolor ikony
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
        leadingIcon = leadingIcon?.let { icon -> // 🔹 Ikona tylko, jeśli podano
            {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
//                    tint = iconTint,
                    modifier = Modifier
                        .size(24.dp)
                )
            }
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Colors.SecondPurple,
            unfocusedIndicatorColor = Colors.SecondPurple,
            unfocusedContainerColor = Color.White, //usuwa szarą poświatę
            focusedContainerColor = Color.White, //usuwa szarą poświatę po kliknięciu
        )
    )
}



