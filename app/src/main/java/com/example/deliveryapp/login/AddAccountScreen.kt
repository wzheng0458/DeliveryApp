package com.example.deliveryapp.login


import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.deliveryapp.login.AccountViewModel
import com.example.deliveryapp.logindatabase.AccountEvent
import com.example.deliveryapp.logindatabase.AccountState

@Composable
fun AddAccountScreen(
    state: AccountState,
    navController: NavController,
    onEvent: (AccountEvent) -> Unit,
    viewModel: AccountViewModel
) {

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {

                onEvent(AccountEvent.SaveAccount(
                    username = state.username.value,
                    password = state.password.value,
                    passwordConfirmation = state.passwordConfirmation.value,
                    email = state.email.value,
                    phoneNumber = state.phoneNumber.value,
                    id = state.id.value,

                    ))
                navController.popBackStack()
            }) {

                Icon(
                    imageVector = Icons.Rounded.Check,
                    contentDescription = "Save Note"
                )

            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = state.username.value,
                onValueChange = {
                    state.username.value = it
                },
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                ),
                placeholder = {
                    Text(text = "username")
                }

            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = state.password.value,
                onValueChange = {
                    state.password.value = it
                },
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                ),
                placeholder = {
                    Text(text = "Password")
                }

            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = state.passwordConfirmation.value,
                onValueChange = {
                    state.passwordConfirmation.value = it
                },
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                ),
                placeholder = {
                    Text(text = "Password Confirmation")
                }

            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = state.email.value,
                onValueChange = {
                    state.email.value = it
                },
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                ),
                placeholder = {
                    Text(text = "Email")
                }

            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = state.phoneNumber.value,
                onValueChange = {
                    state.phoneNumber.value = it
                },
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                ),
                placeholder = {
                    Text(text = "Phone Number")
                }

            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                value = state.id.value,
                onValueChange = {
                    state.id.value = it
                },
                textStyle = TextStyle(
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 17.sp
                ),
                placeholder = {
                    Text(text = "Id")
                }

            )

        }

    }

}