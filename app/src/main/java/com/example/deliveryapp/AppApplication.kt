package com.example.deliveryapp

import android.app.Application
import androidx.room.Room
import com.example.deliveryapp.database.AddressDatabase
import com.example.deliveryapp.database.CustomerDatabase


class AppApplication : Application() {

    companion object {
        lateinit var addressDatabase: AddressDatabase
        lateinit var customerDatabase: CustomerDatabase
//        lateinit var customerAddressListDatabase: CustomerAddressListDatabase
    }

    override fun onCreate() {
        super.onCreate()
        customerDatabase = Room.databaseBuilder(
            applicationContext,
            CustomerDatabase::class.java,
            CustomerDatabase.NAME
        )
            .fallbackToDestructiveMigration()
            .build()

        addressDatabase = Room.databaseBuilder(
            applicationContext,
            AddressDatabase::class.java,
            AddressDatabase.NAME
        )
            .fallbackToDestructiveMigration()
            .build()
//        customerAddressListDatabase = Room.databaseBuilder(
//            applicationContext,
//            CustomerAddressListDatabase::class.java,
//            CustomerAddressListDatabase.NAME
//        )
//            .fallbackToDestructiveMigration()
//            .build()



    }
}