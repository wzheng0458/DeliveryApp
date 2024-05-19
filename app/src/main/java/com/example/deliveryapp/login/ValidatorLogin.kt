package com.example.deliveryapp.login



class ValidatorLogin {
    fun validate1(state1: ValidationData): Boolean {
        var isValid = true

        if (state1.username.isBlank() || !state1.usernameError.isNullOrEmpty()) {
            isValid = false
        }

        if (state1.password.isBlank() || !state1.passwordError.isNullOrEmpty()) {
            isValid = false
        }
        return isValid
    }
}
