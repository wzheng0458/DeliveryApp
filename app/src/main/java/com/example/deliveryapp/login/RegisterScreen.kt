package com.example.loginapp

import android.annotation.SuppressLint
import android.service.autofill.Validator
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.deliveryapp.Screens
import com.example.deliveryapp.login.RegisterEvent
import com.example.loginapp.ui.theme.RegisterViewModel


@SuppressLint("SuspiciousIndentation")
@Composable
fun RegisterScreen(navController: NavController, viewModel: RegisterViewModel) {

    val state = viewModel.state
    val viewModel = viewModel<RegisterViewModel>()
    val context = LocalContext.current
    LaunchedEffect(key1 = context) {
        viewModel.validationRegisterEvents.collect { event ->
            when (event) {
                is RegisterViewModel.ValidationEvent.Success -> {
                    Toast.makeText(
                        context,
                        "Validation successful",
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
        IconButton(
            onClick = { navController.navigate(Screens.LoginMainScreen.name) },
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.Start),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "",
                tint = Color.Gray,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
        }

        Text(
            "Register",
            fontSize = 50.sp,
            modifier = Modifier.padding(16.dp)
        )

        Spacer(modifier = Modifier.padding(16.dp))
        TextField(
            value = state.username,
            onValueChange = {
                viewModel.onEvent(RegisterEvent.UsernameChanged(it))
            },
            isError = state.usernameError != null,
            placeholder = {
                Text(text = "Username")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
        )
        if (state.usernameError != null) {
            Text(
                text = state.usernameError,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.padding(16.dp))

        TextField(
            value = state.password,
            onValueChange = {
                viewModel.onEvent(RegisterEvent.PasswordChanged(it))
            },
            isError = state.passwordError != null,
            placeholder = {
                Text(text = "Password")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        if (state.passwordError != null) {
            Text(
                text = state.passwordError,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.padding(16.dp))

        TextField(
            value = state.passwordConfirmation,
            onValueChange = {
                viewModel.onEvent(RegisterEvent.PasswordConfirmationChanged(it))
            },
            isError = state.passwordConfirmationError != null,
            placeholder = {
                Text(text = "Password Confirmation")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            visualTransformation = PasswordVisualTransformation()
        )
        if (state.passwordConfirmationError != null) {
            Text(
                text = state.passwordConfirmationError,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.padding(16.dp))

        TextField(
            value = state.email,
            onValueChange = {
                viewModel.onEvent(RegisterEvent.EmailChanged(it))
            },
            isError = state.emailError != null,
            placeholder = {
                Text(text = "Email")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),

            )
        if (state.emailError != null) {
            Text(
                text = state.emailError,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.padding(16.dp))

        TextField(
            value = state.phoneNumber,
            onValueChange = {
                viewModel.onEvent(RegisterEvent.PhoneNumberChanged(it))
            },
            isError = state.phoneNumberError != null,
            placeholder = {
                Text(text = "Phone Number")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number
            ),

            )
        if (state.phoneNumberError != null) {
            Text(
                text = state.phoneNumberError,
                color = MaterialTheme.colorScheme.error
            )
        }
        Spacer(modifier = Modifier.padding(16.dp))

        Button(
            onClick = {
                val validationOutput = viewModel.state
                val validator = Validator()
                if (validator.validate(validationOutput)) {
                    viewModel.onEvent(RegisterEvent.Submit)
                    navController.navigate(Screens.LoginMainScreen.name)
                } else {
                    viewModel.onEvent(RegisterEvent.Submit)

                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Submit")
        }
    }
}



