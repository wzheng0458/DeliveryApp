package com.example.deliveryapp.login

sealed class RegisterEvent {
    data class UsernameChanged(val username:String):RegisterEvent()
    data class EmailChanged(val email:String):RegisterEvent()
    data class PasswordChanged(val password:String):RegisterEvent()
    data class PasswordConfirmationChanged(val passwordConfirmation:String):RegisterEvent()
    data class PhoneNumberChanged(val phoneNumber:String):RegisterEvent()
    object Submit: RegisterEvent()
}