package com.example.deliveryapp.login


sealed class LoginEvent {
    data class LoginUsername(val username1: String): LoginEvent()
    data class LoginPassword(val password1: String): LoginEvent()
    object Login : LoginEvent()

}
