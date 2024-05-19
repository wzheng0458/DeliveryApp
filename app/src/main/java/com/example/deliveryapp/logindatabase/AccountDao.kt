package com.example.deliveryapp.logindatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {

    @Query("SELECT * FROM Account")
    fun getAllAccount():LiveData<List<Account>>

    @Insert
    fun addAccount(account: Account)

    @Delete
    fun deleteAccount(account:Account)


    @Query("SELECT * FROM account ORDER BY username ASC")
    fun getAccountOrderedByUsernameAdded(): Flow<List<Account>>

    @Query("SELECT * FROM account ORDER BY id ")
    fun getIdOrderedByUsernameAdded(): Flow<List<Account>>

}

