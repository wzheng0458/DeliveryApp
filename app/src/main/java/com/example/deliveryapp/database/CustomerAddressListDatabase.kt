package com.example.deliveryapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Customer::class, Address::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class CustomerAddressListDatabase: RoomDatabase() {
    abstract fun customerAddressDao(): CustomerAddressDao

    companion object {
        @Volatile
        private var INSTANCE: CustomerAddressListDatabase? = null

        fun getInstance(context: Context): CustomerAddressListDatabase {
            val customerAddressListInstance = INSTANCE
            if (customerAddressListInstance != null) {
                return customerAddressListInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CustomerAddressListDatabase::class.java,
                    "customerAddressList_database"
                ).allowMainThreadQueries().build()

                INSTANCE = instance
                return instance
            }
        }
    }
}

