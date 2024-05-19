package com.example.deliveryapp.logindatabase



sealed class AccountEvent {
    data class DeleteAccount(val account: Account):AccountEvent()
    data class SaveAccount(
        val username:String,
        val password:String,
        val passwordConfirmation:String,
        val email:String,
        val phoneNumber:String,
        val id:String
    ):AccountEvent()

    object SortAccount: AccountEvent()
}