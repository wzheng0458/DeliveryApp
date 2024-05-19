package com.example.deliveryapp.deliverydatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ConfirmDeliveryOrderDao {
    @Query("SELECT * FROM ConfirmDeliveryOrder where deliveryOwnerId = :deliveryOwnerId  ORDER BY createdAt DESC")
    fun getAllOrderById(deliveryOwnerId : String) : LiveData<List<ConfirmDeliveryOrder>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(order: ConfirmDeliveryOrder)

    @Query("Delete FROM ConfirmDeliveryOrder where id = :id")
    suspend fun deleteOrder(id: Int)
}