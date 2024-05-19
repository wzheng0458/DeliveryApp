package com.example.deliveryapp.login


class ValidatePassword {

    fun validation(password: String):ValidationOutput{
        if (password.isBlank()) {
            return ValidationOutput(
                successful = false,
                errorMessage = "The password  cannot be left blank"
            )
        }
        if(password.length < 6){
            return ValidationOutput(
                successful = false,
                errorMessage = "The password needs to have at least 6 characters"
            )
        }
        val lettersAndDigits = password.any{it.isDigit()} &&
                password.any{it.isLetter()}
        if(!lettersAndDigits){
            return ValidationOutput(
                successful = false,
                errorMessage = "The password needs to have at least one letter and a digit"
            )
        }
        return ValidationOutput(
            successful = true
        )
    }


}