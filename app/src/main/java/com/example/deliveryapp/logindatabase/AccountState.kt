package com.example.deliveryapp.logindatabase

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class AccountState(
    val account: List<Account> = emptyList(),
    val accounts: List<Account> = emptyList(),
    val username:MutableState<String> = mutableStateOf(""),
    val password:MutableState<String> = mutableStateOf(""),
    val passwordConfirmation:MutableState<String> = mutableStateOf(""),
    val email:MutableState<String> = mutableStateOf(""),
    val phoneNumber:MutableState<String> = mutableStateOf(""),
    val id:MutableState<String> = mutableStateOf(""),

    )
