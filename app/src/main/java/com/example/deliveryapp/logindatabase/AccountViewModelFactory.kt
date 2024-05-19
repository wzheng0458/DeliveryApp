package com.example.deliveryapp.logindatabase

import com.example.deliveryapp.login.AccountViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class AccountViewModelFactory(private val dao: AccountDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AccountViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AccountViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}