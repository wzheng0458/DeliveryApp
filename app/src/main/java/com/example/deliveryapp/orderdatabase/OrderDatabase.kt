package com.example.deliveryapp.orderdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.deliveryapp.bookingdatabase.Booking
import com.example.deliveryapp.bookingdatabase.BookingDao


@Database(
    entities = [Order::class],
    version = 2,
    exportSchema = false
)
abstract class OrderDatabase:RoomDatabase()  {

    companion object {
        const  val NAME= "Order_db"
    }

    abstract fun getOrderDao(): OrderDao
}