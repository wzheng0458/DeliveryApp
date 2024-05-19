package com.example.loginapp

import com.example.deliveryapp.login.ValidationData


class Validator {
    fun validate(state: ValidationData): Boolean {
        var isValid = true

        if (state.username.isBlank() || !state.usernameError.isNullOrEmpty()) {
            isValid = false
        }

        if (state.password.isBlank() || !state.passwordError.isNullOrEmpty()) {
            isValid = false
        }

        if (state.passwordConfirmation.isBlank() || state.password != state.passwordConfirmation ||
            !state.passwordConfirmationError.isNullOrEmpty()) {
            isValid = false
        }

        if (state.email.isBlank() || !state.emailError.isNullOrEmpty()) {
            isValid = false
        }

        if (state.phoneNumber.isBlank() || !state.phoneNumberError.isNullOrEmpty()) {
            isValid = false
        }

        return isValid
    }
}
