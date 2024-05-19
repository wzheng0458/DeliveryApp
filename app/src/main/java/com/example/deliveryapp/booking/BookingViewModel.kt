package com.example.deliveryapp.booking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.bookingdatabase.Booking
import com.example.deliveryapp.delivery.AppApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class BookingViewModel : ViewModel() {

    val booking = AppApplication.bookingDatabase.getBookingDao()

    val BookingList: LiveData<List<Booking>> = booking.printAllRecord()

    fun addBooking(
        Name: String,
        state: String,
        Date: String,
        Stime: String,
        Etime: String,
        tableNum: String,
        ID: String
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            booking.insertRecord(Booking(tableNum, Date, Stime, Etime, state, Name, ID))
        }
    }


    fun deleteBooking(ID: String, ) {
        viewModelScope.launch(Dispatchers.IO) {
            booking.deleteRecord(ID)
        }
    }

    fun printBookingbyName(Name:String): LiveData<List<Booking>> {
        return booking.printUserInfo(Name)
    }



}