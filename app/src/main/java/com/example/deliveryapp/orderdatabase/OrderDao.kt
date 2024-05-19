package com.example.deliveryapp.orderdatabase


import androidx.room.Dao
import androidx.room.Insert



@Dao
interface OrderDao {
    @Insert
    suspend fun insert(order: Order)


}