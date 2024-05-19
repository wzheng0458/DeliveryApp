package com.example.deliveryapp.bookingdatabase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface BookingDao {
    @Query("delete  from Booking where BookingId=:id")
    suspend fun deleteRecord( id:String)

    @Insert
    suspend fun insertRecord(Booking: Booking)

    @Query("select * from Booking where CustomerName")
    fun printAllRecord(): LiveData<List<Booking>>

    @Query("select * from Booking where CustomerName = :userID")
    fun printUserInfo(userID:String):LiveData<List<Booking>>

    @Query("select * from Booking where BookingId=:bookingId")
    fun printBookingId(bookingId:String):List<Booking>

    @Query("select * from Booking where TableNum=:TableNum")
    fun printTableNum(TableNum:String):List<Booking>


}