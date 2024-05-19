package com.example.deliveryapp.bookingdatabase


import androidx.room.PrimaryKey

@androidx.room.Entity(tableName = "Booking")
data class Booking(
    val TableNum: String,
    val Date: String,
    val StartTime: String,
    val EndtTime: String,
    val State: String,
    val CustomerName: String,
    @PrimaryKey(autoGenerate = false)
    val BookingId: String ,
)