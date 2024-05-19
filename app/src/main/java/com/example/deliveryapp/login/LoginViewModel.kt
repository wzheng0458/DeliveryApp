package com.example.deliveryapp.login


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

import androidx.lifecycle.ViewModel
import com.example.deliveryapp.delivery.AppApplication

import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

class LoginViewModel(
    private val validateUsername: ValidateUsername = ValidateUsername(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
) : ViewModel() {

    val accountDao = AppApplication.accountDatabase.getAccountDao()
    var state1 by mutableStateOf(ValidationData())
    private val loginEventChannel = Channel<ValidationEvent>()
    val loginEvents = loginEventChannel.receiveAsFlow()

    fun onLogin(event: LoginEvent) {
        when (event) {
            is LoginEvent.LoginUsername -> {
                state1 = state1.copy(username = event.username1)
            }
            is LoginEvent.LoginPassword -> {
                state1 = state1.copy(password = event.password1)
            }
            is LoginEvent.Login -> {

                validateLogin()
            }
        }
    }

    private fun validateLogin() {
        val usernameResult = validateUsername.validation(state1.username)
        val passwordResult = validatePassword.validation(state1.password)

        val hasError = listOf(
            usernameResult,
            passwordResult
        ).any { !it.successful }

        if (hasError) {
            state1 = state1.copy(
                usernameError = usernameResult.errorMessage,
                passwordError = passwordResult.errorMessage
            )
            return
        }else {
            state1 = state1.copy(
                passwordError = null,
                usernameError = null,
            )
        }
        Login(state1.username, state1.password)


    }

    private fun Login(username: String, password: String): Boolean {
        var isMatchFound = false
        accountDao.getAllAccount().observeForever { accounts ->
            for (account in accounts) {
                if (account.username == username && account.password == password) {
                    isMatchFound = true
                    break
                }
            }
            if (!isMatchFound) {

                state1 = state1.copy(
                    usernameError = "Invalid username ",
                    passwordError = "Invalid password"
                )
            }


        }
        return isMatchFound

    }


    sealed class ValidationEvent {
        object Success : ValidationEvent()
        object Failure : ValidationEvent()
    }
}


