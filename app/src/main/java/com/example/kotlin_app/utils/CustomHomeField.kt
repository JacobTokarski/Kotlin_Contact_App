    package com.example.kotlin_app.utils

    // To będzie custom text field strikte pod ekran Home i ekran dialogowy

    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.shape.RoundedCornerShape
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
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp

    @Composable
    fun CustomHomeField(
        value: String,
        onValueChange: (String) -> Unit,
        label: String,

    ) {

        var nameError by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = value,
            onValueChange = {
                onValueChange(it)
                nameError = it.isNotEmpty() && !it.all { char -> char.isLetter() }
            },
            isError = nameError,
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

        if (nameError) {
        Text(
            text = "Only letters are allowed - please try again!",
            color = Color.Red,
            fontSize = 13.sp,
            modifier = Modifier.padding(start = 16.dp, top = 4.dp)
        )
    }
}
