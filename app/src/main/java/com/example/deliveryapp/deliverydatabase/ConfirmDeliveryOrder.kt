package com.example.deliveryapp.deliverydatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "ConfirmDeliveryOrder"
)
data class ConfirmDeliveryOrder (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val deliveryAddress: String,
    val deliveryMeetOption: String,
    val deliveryUnit: String,
    val deliveryState: String,
    val deliveryDesc: String,
    val deliveryOwnerId: String,
    val deliveryStatus: Boolean,
    val deliveryDate: String,
    val deliveryTime: String, // Using a timestamp (or Date/LocalDate) for date
    val createdAt: Date
)

