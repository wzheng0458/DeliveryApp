package com.example.deliveryapp.login


import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.deliveryapp.Screens



@Composable
fun LoginMainScreen(navController: NavController, viewModel: LoginViewModel) {

    val state1 = viewModel.state1
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.loginEvents.collect { event ->
            when (event) {
                is LoginViewModel.ValidationEvent.Success -> {
                    Toast.makeText(
                        context,
                        "Validation successful",
                        Toast.LENGTH_LONG
                    ).show()
                }

                LoginViewModel.ValidationEvent.Failure -> {
                    Toast.makeText(
                        context,
                        "Validation failed",
                        Toast.LENGTH_LONG
                    ).show()
                }

                else -> {}
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp)
    ) {

        Text(
            text = "Welcome!",
            fontSize = 50.sp,
            modifier = Modifier.padding(16.dp)
        )
        TextField(
            value = state1.username,
            onValueChange = {
                viewModel.onLogin(LoginEvent.LoginUsername(it))
            },
            isError = state1.usernameError != null,
            placeholder = {
                Text(text = "Username")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
        )
        if (state1.usernameError != null) {
            Text(
                text = state1.usernameError,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.padding(12.dp))
        TextField(
            value = state1.password,
            onValueChange = {
                viewModel.onLogin(LoginEvent.LoginPassword(it))
            },
            isError = state1.passwordError != null,
            placeholder = {
                Text(text = "Password")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        if (state1.passwordError != null) {
            Text(
                text = state1.passwordError,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.padding(12.dp))
        Row() {
            Button(
                onClick = {
                    val validator = ValidatorLogin()
                    val validationOutput = viewModel.state1
                    if (validationOutput.username == "admin123" && validationOutput.password == "admin123") {
                        navController.navigate(Screens.StaffMainScreen.name + "/${validationOutput.username}")
                    } else {
                        if (validator.validate1(validationOutput)) {
                            viewModel.onLogin(LoginEvent.Login)
                            navController.navigate(Screens.CustomerMainScreen.name + "/${state1.username}")
                        } else {
                            viewModel.onLogin(LoginEvent.Login)
                        }

                    }
                },
                modifier = Modifier
                    .padding(12.dp)
                    .width(120.dp)
            ) {
                Text("Login")
            }
            Button(
                onClick = { navController.navigate(Screens.RegisterScreen.name) },
                modifier = Modifier
                    .padding(12.dp)
                    .width(120.dp)

            ) {
                Text("Register")
            }
        }
    }
}