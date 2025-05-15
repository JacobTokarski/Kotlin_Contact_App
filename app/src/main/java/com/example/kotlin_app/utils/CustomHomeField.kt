    package com.example.kotlin_app.utils

    // To będzie custom text field strikte pod ekran Home i ekran dialogowy

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
    fun CustomHomeField(
        value: String,
        onValueChange: (String) -> Unit,
        label: String,

    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text(text = label)},
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