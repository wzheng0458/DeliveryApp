package com.example.deliveryapp.logindatabase

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.loginapp.ui.theme.AccountDao

@Database(
    entities = [Account::class],
    version = 5
)
abstract class AccountDatabase: RoomDatabase() {

    companion object{
        const val NAME = "Account_DB"
    }

    abstract fun getAccountDao(): AccountDao

}