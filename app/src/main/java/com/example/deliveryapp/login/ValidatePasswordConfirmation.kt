package com.example.deliveryapp.login

class ValidatePasswordConfirmation {

    fun validation(password: String, passwordConfirmation: String): ValidationOutput {
        if (password != passwordConfirmation) {
            return ValidationOutput(
                successful = false,
                errorMessage = "The passwords are not the same"
            )
        }
        if (passwordConfirmation.isBlank()) {
            return ValidationOutput(
                successful = false,
                errorMessage = "The password confirmation cannot be left blank"
            )
        }
        return ValidationOutput(
            successful = true
        )
    }
}