package com.example.loginapp.ui.theme


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.delivery.AppApplication
import com.example.deliveryapp.logindatabase.Account

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AccountViewModel : ViewModel() {

    val accountDao = AppApplication.accountDatabase.getAccountDao()


    val accountList : LiveData<List<Account>> = accountDao.getAllAccount()


    fun addAccount(
        username:String,password:String,passwordConfirmation:String,
        email:String,phoneNumber:String,id: String){
        viewModelScope.launch(Dispatchers.IO) {
            accountDao.addAccount(Account
                (username = username, password = password,
                passwordConfirmation = passwordConfirmation ,
                email = email , phoneNumber = phoneNumber,
                id = id))
        }
    }

    fun deleteAccount(id : String){
        viewModelScope.launch(Dispatchers.IO) {
            accountDao.deleteAccount(id)
        }
    }


}