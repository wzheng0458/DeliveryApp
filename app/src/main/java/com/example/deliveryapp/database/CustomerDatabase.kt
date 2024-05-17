package com.example.deliveryapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Customer::class], version = 5, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CustomerDatabase : RoomDatabase(){

    companion object {
        const val NAME = "Customer_DB"
    }
    abstract fun getCustomerDao() : CustomerDao

}