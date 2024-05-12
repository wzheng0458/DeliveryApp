package com.example.deliveryapp.database


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Customer")
data class Customer(
    @PrimaryKey(autoGenerate = false)
    var customerId: String,
    var name: String,
    var email: String,
    var phoneNum: String,
)



