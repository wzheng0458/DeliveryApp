package com.example.deliveryapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date


@Entity(
    tableName = "Address",
)
data class Address(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val address: String,
    val meetOption: String,
    val unit: String,
    val state: String,
    val desc: String,
    var ownerId: String,
    val createdAt: Date,

    )


