package com.example.deliveryapp

import android.app.Application
import androidx.room.Room
import com.example.deliveryapp.database.AddressDatabase
import com.example.deliveryapp.database.ConfirmDeliveryOrderDatabase
import com.example.deliveryapp.database.CustomerDatabase


class AppApplication : Application() {

    companion object {
        lateinit var addressDatabase: AddressDatabase
        lateinit var customerDatabase: CustomerDatabase
        lateinit var confirmDeliveryOrderDatabase: ConfirmDeliveryOrderDatabase

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

        confirmDeliveryOrderDatabase = Room.databaseBuilder(
            applicationContext,
            ConfirmDeliveryOrderDatabase::class.java,
            ConfirmDeliveryOrderDatabase.NAME
        )
            .fallbackToDestructiveMigration()
            .build()



    }
}