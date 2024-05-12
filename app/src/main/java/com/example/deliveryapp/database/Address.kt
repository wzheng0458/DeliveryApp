package com.example.deliveryapp.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import java.util.Date


@Entity(
    tableName = "Address",
    foreignKeys = [ForeignKey(
        entity = Customer::class,
        parentColumns = ["customerId"],
        childColumns = ["customerId"],
        onDelete = CASCADE,
        onUpdate = CASCADE)
    ]
)
data class Address(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var address: String,
    var customerId: String,
    var createdAt: Date,

    )


