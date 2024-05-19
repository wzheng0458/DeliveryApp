package com.example.deliveryapp.bookingdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter

@Database(
    entities = [Booking::class],
    version = 1,
    exportSchema = false
)
abstract class BookingDatabase:RoomDatabase()  {

    companion object {
        const  val NAME= "Booking"
    }

    abstract fun getBookingDao():BookingDao
}