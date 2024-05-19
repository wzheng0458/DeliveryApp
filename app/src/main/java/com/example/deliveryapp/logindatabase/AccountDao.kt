package com.example.loginapp.ui.theme

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.deliveryapp.logindatabase.Account
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {


    @Query("SELECT * FROM Account ORDER BY username ASC")
    fun getAllAccount() : LiveData<List<Account>>

    @Insert
    fun addAccount(account : Account)

    @Query("Delete FROM Account where id = :id")
    fun deleteAccount(id : String)
}

