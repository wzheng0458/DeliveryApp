package com.example.deliveryapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [ConfirmDeliveryOrder::class], version = 2, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ConfirmDeliveryOrderDatabase: RoomDatabase() {
    companion object {
        const val NAME = "ConfirmDeliveryOrder_DB"
    }
    abstract fun getConfirmDeliveryOrderDao() : ConfirmDeliveryOrderDao
}