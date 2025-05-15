package com.example.kotlin_app.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.airbnb.lottie.compose.*
import com.example.kotlin_app.R
import com.example.kotlin_app.db.Todo
import com.example.kotlin_app.utils.Colors
import com.example.kotlin_app.utils.CustomHomeField
import com.example.kotlin_app.utils.CustomPhoneField
import com.example.kotlin_app.utils.Routes
import com.example.kotlin_app.viewmodel.AuthState
import com.example.kotlin_app.viewmodel.AuthViewModel
import com.example.kotlin_app.viewmodel.HomeViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun HomePage(
    modifier: Modifier,
    navController: NavHostController,
    authViewModel: AuthViewModel,
    viewModel: HomeViewModel
) {
    val todoList by viewModel.todoList.observeAsState()
    var showDialog by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }

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

    Column {
        Row(
            modifier = Modifier
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    authViewModel.signout()
                }
                .padding(start = 12.dp, top = 55.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.arrow),
                contentDescription = "Back Icon",
                modifier = Modifier.size(20.dp)
            )
            Text(
                text = "Back",
                color = Colors.PrimaryPurple,
                fontSize = 12.sp,
                fontWeight = FontWeight(400)
            )
        }

        Spacer(modifier = Modifier.height(8.dp))


        Box(modifier = Modifier.fillMaxSize()) {
            if (todoList.isNullOrEmpty()) {
                val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty_contact))
                val progress by animateLottieCompositionAsState(
                    composition,
                    iterations = LottieConstants.IterateForever
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    LottieAnimation(
                        composition = composition,
                        progress = { progress },
                        modifier = Modifier.size(350.dp)
                    )
                    Spacer(modifier = Modifier.height(15.dp))

                    Text(
                        text = "Brak istniejących kontaktów",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Dodaj swój pierwszy kontakt przyciskiem + ",
                        fontSize = 15.sp,
                        color = Color.Gray,
                        textAlign = TextAlign.Center
                    )
                }
            } else {
                LazyColumn {
                    itemsIndexed(todoList!!) { _, item ->
                        TodoItem(item = item) {
                            viewModel.deleteTodo(item.id)
                        }
                    }
                }
            }

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
        }

        if (showDialog) {
            AlertDialog(
                containerColor = Colors.BackgroundColor,
                onDismissRequest = { showDialog = false },
                title = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            fontWeight = FontWeight.Bold,
                            fontSize = 20.sp,
                            text = "Nowy kontakt", )
                    }
                },
                text = {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Person icon",
                            tint = Color.Black,
                            modifier = Modifier
                                .size(120.dp)
                                .clip(RoundedCornerShape(70.dp))
                                .background(Colors.ThirdPurple)
                                .padding(16.dp)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        CustomHomeField(
                            value = name,
                            onValueChange = { name = it },
                            label = "Imię"
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        CustomHomeField(
                            value = surname,
                            onValueChange = { surname = it },
                            label = "Nazwisko"
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        CustomPhoneField(
                            value = phone,
                            onValueChange = { phone = it }
                        )
                    }
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            if (phone.isNotBlank() && name.isNotBlank() && surname.isNotBlank() && !phoneError) {
                                viewModel.addTodo("$name $surname tel: $phone")
                                showDialog = false
                                name = ""
                                surname = ""
                                phone = ""
                            }
                        }
                    ) {
                        Text("Add")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancel")
                    }
                }
            )
        }
    }
}

@Composable
fun TodoItem(
    item: Todo,
    onDelete: () -> Unit
) {
    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Colors.PrimaryPurple)
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 12.dp)
            ) {
                Text(
                    text = SimpleDateFormat("HH:mm, dd/MM", Locale.ENGLISH).format(item.createdAt),
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = item.title,
                    fontSize = 22.sp,
                    color = Color.White,
                    lineHeight = 28.sp,
                    modifier = Modifier.clickable {
                        val phoneRegex = Regex("""tel:\s*(\d+)""")
                        val match = phoneRegex.find(item.title)
                        match?.groupValues?.get(1)?.let { number ->
                            val fullNumber = "+48 $number"
                            val intent = Intent(Intent.ACTION_DIAL).apply {
                                data = Uri.parse("tel:$fullNumber")
                            }
                            context.startActivity(intent)
                        }
                    }
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
}






