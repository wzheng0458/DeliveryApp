package com.example.deliveryapp.login


class ValidatePhoneNumber {
    fun validation(phoneNumber: String): ValidationOutput {
        if (phoneNumber.isBlank()) {
            return ValidationOutput(
                successful = false,
                errorMessage = "The phone number cannot be left blank"
            )
        }
        val digits = phoneNumber.any { it.isDigit() }
        if (!digits) {
            return ValidationOutput(
                successful = false,
                errorMessage = "The phone number you've entered can only contain digits"
            )
        }
        if (phoneNumber.length < 10 || phoneNumber.length > 11) {
            return ValidationOutput(
                successful = false,
                errorMessage = "The phone number you've entered should have 10-11 characters only"
            )
        }


        return ValidationOutput(
            successful = true
        )
    }
}