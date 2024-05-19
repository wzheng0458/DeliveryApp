package com.example.deliveryapp.booking

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import com.example.deliveryapp.bookingdatabase.Booking
import com.example.deliveryapp.booking.BookingViewModel
import com.example.deliveryapp.booking.ViewModel
import java.time.Duration
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class TimeViewModel : ViewModel() {
    fun TimeValidate(Stime: String?, Etime: String?, Date: String): Int {
        val currentDate = Calendar.getInstance()
        val currentMonth = currentDate.get(Calendar.MONTH) + 1
        val currentTime = LocalTime.now()
        val currentDay = currentDate.get(Calendar.DAY_OF_MONTH)
        var valid: Int = 0
        val formatter = DateTimeFormatter.ofPattern("hh:mm a")
        val StartRange = LocalTime.of(8, 0)
        val EndRange = LocalTime.of(22, 0)


        if (Stime.isNullOrBlank() || Etime.isNullOrBlank() || Date.isNullOrBlank()) {
            return valid
        } else {
            //time variable
            val Start = LocalTime.parse(Stime, formatter)
            val End = LocalTime.parse(Etime, formatter)
            val duration = Duration.between(Start, End)
            val minute = duration.toMinutes()
            val isWithinrangeStart = Start in StartRange..EndRange
            val isWithinrangeEnd = End in StartRange..EndRange

            //date variable

            val parts = Date.split("/")

            val month = parts[1].toIntOrNull()
            val day = parts[0].toIntOrNull()

            if (isWithinrangeStart && isWithinrangeEnd) {
                if (minute < 30 || minute > 120) {

                    valid = 2
                    /*set maximum and minimum time*/
                } else {
                    //the booking time must be late 30 minutes than current time
                    if (month == currentMonth && day == currentDay) {
                        if (Start.isBefore(currentTime.plusMinutes(31))) {
                            valid = 1
                        } else {
                            valid = 0
                        }
                    }
                }
            }else{
                valid=3
            }
        }
        return valid
    }

    fun TimeCombination(hour: Int, minute: Int): String {
        var time: String = ""
        if (hour > 12) {
            if ((hour - 12) < 10) {
                if (minute < 10) {
                    time = "0${hour - 12}:0$minute PM"
                } else if (minute > 10) {
                    time = "0${hour - 12}:$minute PM"
                }
            } else {
                if (minute < 10) {
                    time = "${hour - 12}:0$minute PM"
                } else if (minute > 10) {
                    time = "${hour - 12}:$minute PM"
                }
            }

        } else if (hour < 12) {
            if (hour < 10) {
                if (minute < 10) {
                    time = "0$hour:0$minute AM"
                } else if (minute > 10) {
                    time = "0$hour:$minute AM"
                }
            } else {
                if (minute < 10) {
                    time = "$hour:0$minute AM"
                } else if (minute > 10) {
                    time = "$hour:$minute AM"
                }
            }
        }
        return time
    }


}