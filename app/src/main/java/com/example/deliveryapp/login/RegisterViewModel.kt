package com.example.loginapp.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.delivery.AppApplication
import com.example.deliveryapp.login.RegisterEvent
import com.example.deliveryapp.login.ValidateEmail
import com.example.deliveryapp.login.ValidatePassword
import com.example.deliveryapp.login.ValidatePasswordConfirmation
import com.example.deliveryapp.login.ValidatePhoneNumber
import com.example.deliveryapp.login.ValidateUsername
import com.example.deliveryapp.login.ValidationData
import com.example.deliveryapp.logindatabase.Account
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val validateUsername: ValidateUsername = ValidateUsername(),
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validatePasswordConfirmation: ValidatePasswordConfirmation = ValidatePasswordConfirmation(),
    private val validatePhoneNumber: ValidatePhoneNumber = ValidatePhoneNumber()
): ViewModel() {

    val accountDao = AppApplication.accountDatabase.getAccountDao()
    val accountList: LiveData<List<Account>> = accountDao.getAllAccount()
    var state by mutableStateOf(ValidationData())


    fun addAccount(username:String,password:String,passwordConfirmation:String,email:String,phoneNumber:String,id:String){
        viewModelScope.launch (Dispatchers.IO){
            accountDao.addAccount(Account(username = username, password = password,passwordConfirmation = passwordConfirmation,email = email,phoneNumber = phoneNumber,id=id))
        }

    }

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationRegisterEvents = validationEventChannel.receiveAsFlow()

    fun onEvent(event: RegisterEvent) {
        when(event) {
            is RegisterEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is RegisterEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is RegisterEvent.PasswordConfirmationChanged -> {
                state = state.copy(passwordConfirmation = event.passwordConfirmation)
            }
            is RegisterEvent.UsernameChanged -> {
                state = state.copy(username = event.username)
            }
            is RegisterEvent.PhoneNumberChanged ->{
                state = state.copy(phoneNumber = event.phoneNumber)
            }
            is RegisterEvent.Submit -> {
                passData()
            }
        }
    }

    private fun submitData() {
        val usernameResult = validateUsername.validation(state.username)
        val phoneNumberResult = validatePhoneNumber.validation(state.phoneNumber)
        val emailResult = validateEmail.validation(state.email)
        val passwordResult = validatePassword.validation(state.password)
        val confirmationPasswordResult = validatePasswordConfirmation.validation(
            state.password, state.passwordConfirmation
        )


        val hasError = listOf(
            emailResult,
            passwordResult,
            confirmationPasswordResult,
            usernameResult,
            phoneNumberResult
        ).any { !it.successful}

        if(hasError) {
            state = state.copy(
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                passwordConfirmationError = confirmationPasswordResult.errorMessage,
                usernameError = usernameResult.errorMessage,
                phoneNumberError = phoneNumberResult.errorMessage

            )
            return
        }else{
            state = state.copy(
                emailError = null,
                passwordError = null,
                passwordConfirmationError = null,
                usernameError = null,
                phoneNumberError = null
            )
        }
        viewModelScope.launch(Dispatchers.IO) {
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    private fun passData(){
        submitData()
        if (state.emailError == null &&
            state.passwordError == null &&
            state.passwordConfirmationError == null &&
            state.usernameError == null &&
            state.phoneNumberError == null
        ) {
            addAccount(
                username = state.username,
                password = state.password,
                passwordConfirmation = state.passwordConfirmation,
                email = state.email,
                phoneNumber = state.phoneNumber,
                id = generateUniqueId()
            )
        }


    }

    private fun generateUniqueId(): String {
        val randomNumber = (Math.random() * 100000).toInt()
        return "C$randomNumber"
    }

    private fun returnNull(){
        state = state.copy(
            emailError = null,
            passwordError = null,
            passwordConfirmationError = null,
            usernameError = null,
            phoneNumberError = null
        )
    }

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}
