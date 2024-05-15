package com.example.deliveryapp.database


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Customer")
data class Customer(
    @PrimaryKey(autoGenerate = false)
    val customerId: String,
    val name: String,
    val email: String,
    val phoneNum: String,
)



