package com.example.deliveryapp.logindatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Account(
    val username:String,
    val password:String,
    val passwordConfirmation:String,
    val email:String,
    val phoneNumber: String,
    @PrimaryKey(autoGenerate = false)
    val id:String
)




