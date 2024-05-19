package com.example.deliveryapp.delivery

import android.app.Application
import androidx.room.Room
import com.example.deliveryapp.bookingdatabase.BookingDatabase
import com.example.deliveryapp.deliverydatabase.AddressDatabase
import com.example.deliveryapp.deliverydatabase.ConfirmDeliveryOrderDatabase
import com.example.deliveryapp.logindatabase.AccountDatabase



class AppApplication : Application() {

    companion object {
        lateinit var addressDatabase: AddressDatabase
        lateinit var confirmDeliveryOrderDatabase: ConfirmDeliveryOrderDatabase
        lateinit var accountDatabase: AccountDatabase
        lateinit var bookingDatabase: BookingDatabase

    }

    override fun onCreate() {
        super.onCreate()

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
        accountDatabase = Room.databaseBuilder(
            applicationContext,
            AccountDatabase::class.java,
            AccountDatabase.NAME
        ).fallbackToDestructiveMigration()
            .build()

        bookingDatabase = Room.databaseBuilder(
            applicationContext,
            BookingDatabase::class.java,
            BookingDatabase.NAME
        )
            .fallbackToDestructiveMigration()
            .build()



    }
}