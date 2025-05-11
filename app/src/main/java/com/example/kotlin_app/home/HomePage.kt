package com.example.kotlin_app.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.kotlin_app.R
import com.example.kotlin_app.db.Todo
import com.example.kotlin_app.utils.Colors
import com.example.kotlin_app.utils.CustomTextField
import com.example.kotlin_app.utils.Routes
import com.example.kotlin_app.viewmodel.AuthState
import com.example.kotlin_app.viewmodel.AuthViewModel
import com.example.kotlin_app.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomePage(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel,
    viewModel: HomeViewModel
) {
    val todoList by viewModel.todoList.observeAsState()
    var inputText by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    var name by remember { mutableStateOf("") }
    var surname by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var phoneError by remember { mutableStateOf(false) }

    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        if (authState.value is AuthState.Unauthenticated) {
            navController.navigate(Routes.loginPage)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(id = R.drawable.arrow),
                    contentDescription = "Back Icon",
                    modifier = Modifier.size(20.dp)
                )
                TextButton(onClick = { authViewModel.signout() }) {
                    Text(
                        text = "Back",
                        color = Colors.PrimaryPurple,
                        fontSize = 12.sp,
                        fontWeight = FontWeight(400)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = inputText,
                    onValueChange = { inputText = it },
                    placeholder = { Text("Wpisz coś") },
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    viewModel.addTodo(inputText)
                    inputText = ""
                }) {
                    Text(text = "Add")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            todoList?.let {
                LazyColumn {
                    itemsIndexed(it) { _, item ->
                        TodoItem(item = item) {
                            viewModel.deleteTodo(item.id)
                        }
                    }
                }
            } ?: Text(
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = "No items yet",
                fontSize = 16.sp
            )
        }

        // Plus – lewy dolny róg
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Add Contact",
                tint = Color.White,
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(Colors.PrimaryPurple)
                    .clickable { showDialog = true }
                    .padding(12.dp)
            )
        }

        // Okienko dialogowe do dodawania kontaktu
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                title = { Text("Nowy kontakt") },
                text = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Person icon",
                            tint = Color.Gray,
                            modifier = Modifier
                                .size(90.dp)
                                .clip(RoundedCornerShape(50.dp))
                                .background(Color.LightGray)
                                .padding(16.dp)

                        )

                        Spacer(modifier = Modifier.height(16.dp)) // odległość między iconą person a pierwszym textfieldem

                        OutlinedTextField(
                            value = name,
                            onValueChange = { name = it },
                            label = { Text("Imię") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp)) // wartość pomiędzy Textfieldem "Imię i nazwisko" oraz "Numer telefonu"

                        OutlinedTextField(
                            value = surname,
                            onValueChange = { surname = it },
                            label = { Text("Nazwisko") },
                            modifier = Modifier.fillMaxWidth()
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        OutlinedTextField(
                            value = phone,
                            onValueChange = {
                                phone = it
                                phoneError = it.isNotEmpty() && !it.all { char -> char.isDigit() }
                            },
                            isError = phoneError,
                            label = { Text("Numer telefonu") },
                            modifier = Modifier.fillMaxWidth(),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                        if (phoneError) {
                            Text(
                                text = "Only numbers are allowed - please try again!",
                                color = Color.Red,
                                fontSize = 13.sp
                            )
                        }
                    }
                },

                confirmButton = {
                    TextButton(
                        onClick = {
                            if (phone.isNotBlank() && name.isNotBlank() && !phoneError) {
                                viewModel.addTodo("$name - $phone")
                                showDialog = false
                                name = ""
                                phone = ""
                            }
                        }
                    ) {
                        Text("Add")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Delete")
                    }
                }
            )
        }
    }
}

@Composable
fun TodoItem(item: Todo, onDelete: () -> Unit) {  //nasza funkcja todoitem która będzie wyświetlać nasze dane kontatkowe
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = SimpleDateFormat("HH:mm, dd/MM", Locale.ENGLISH).format(item.createdAt),
                fontSize = 12.sp,
                color = Color.White
            )
            Text(
                text = item.title,
                fontSize = 20.sp,
                color = Color.White
            )
        }
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_delete_24),
                contentDescription = "Delete",
                tint = Color.White
            )
        }
    }
}




