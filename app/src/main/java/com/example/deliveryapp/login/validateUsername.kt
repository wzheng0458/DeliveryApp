package com.example.deliveryapp.login


class ValidateUsername {

    fun validation(username: String):ValidationOutput{
        if(username.isBlank()){
            return ValidationOutput(
                successful = false,
                errorMessage = "The username cannot be left blank"
            )
        }
        if(username.length < 4 || username.length > 30){
            return ValidationOutput(
                successful = false,
                errorMessage = "The username should have at least 4-30 characters only"
            )
        }
        val lettersAndDigits = username.any{it.isDigit()} &&
                username.any{it.isLetter()}
        if(!lettersAndDigits){
            return ValidationOutput(
                successful = false,
                errorMessage = "The username needs to have at least one letter , a digit and no special symbols"
            )
        }

        return ValidationOutput(
            successful = true
        )

    }

}