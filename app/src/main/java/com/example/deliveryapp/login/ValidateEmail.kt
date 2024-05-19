package com.example.deliveryapp.login

import android.util.Patterns
import com.example.deliveryapp.login.ValidationOutput

class ValidateEmail {

    fun validation(email: String): ValidationOutput {
        if(email.isBlank()){
            return ValidationOutput(
                successful = false,
                errorMessage = "The email can't be left in blank"
            )
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            return ValidationOutput(
                successful = false,
                errorMessage = "This is not a valid email"
            )
        }
        return ValidationOutput(
            successful = true
        )
    }


}