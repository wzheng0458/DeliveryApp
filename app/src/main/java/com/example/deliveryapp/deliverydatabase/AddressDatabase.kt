package com.example.deliveryapp.deliverydatabase


import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [Address::class], version = 9, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AddressDatabase : RoomDatabase(){

    companion object {
        const val NAME = "Address_DB"
    }
    abstract fun getAddressDao() : AddressDao

}