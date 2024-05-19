package com.example.deliveryapp.login



data class ValidationData(
    val username:String = "",
    val usernameError:String? = null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "" ,
    val passwordError:String? = null,
    val passwordConfirmation:String = "",
    val passwordConfirmationError: String? =null,
    val phoneNumber:String = "",
    val phoneNumberError:String? = null,

    )
