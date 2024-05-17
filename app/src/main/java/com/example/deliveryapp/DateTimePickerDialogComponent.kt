package com.example.deliveryapp

import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePickerDialogComponent(
    onDateSelected: (String) -> Unit,
    onTimeSelected: (String) -> Unit
){
    val currentDate = Calendar.getInstance().apply { timeInMillis = System.currentTimeMillis() }
    val maxDate = Calendar.getInstance().apply { add(Calendar.DATE, 3) }
    // Initial state setup for the DatePickerDialog. Specifies to show the picker initially
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)
    // State to hold the selected date as a String
    val selectedDateLabel = remember { mutableStateOf("") }
    // State to control the visibility of the DatePickerDialog
    val openDateDialog = remember { mutableStateOf(false) }
    // Define the main color for the calendar picker
    val calendarPickerMainColor = Color(0xFF722276)
    val cal = Calendar.getInstance()
    val hour = cal.get(Calendar.HOUR_OF_DAY)
    val minute = cal.get(Calendar.MINUTE)

    val context = LocalContext.current
    var time by remember {
        mutableStateOf("")
    }
    val timePickerDialog = TimePickerDialog(
        context,{ t, hoursOfDay, minutes ->
            val selectedTime = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hoursOfDay)
                set(Calendar.MINUTE, minutes)
            }

            if (selectedTime.after(currentDate) && selectedTime.before(maxDate)) {
                time = String.format("%02d:%02d", hoursOfDay, minutes)
                onTimeSelected(time)
            } else {
                time = ""
                Toast.makeText(context, "Invalid time input", Toast.LENGTH_LONG).show()
            }
        }, hour, minute, false
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "When?",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .clickable { openDateDialog.value = true },
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFA500))
        ) {
            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Date Picker"
                )
                Text(
                    text = "Date: ${selectedDateLabel.value}",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
                .clickable { timePickerDialog.show() },
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(18.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFA500))
        ) {
            Row(
                modifier = Modifier.padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AccessTime,
                    contentDescription = "Time Picker"
                )
                Text(
                    text = "Time: ${time}",
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }

        if (openDateDialog.value) {
            DatePickerDialog(
                colors = DatePickerDefaults.colors(
                    containerColor = Color(0xFFF5F0FF),
                ),
                onDismissRequest = {
                    openDateDialog.value = false
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            openDateDialog.value = false
                            selectedDateLabel.value =
                                datePickerState.selectedDateMillis?.convertMillisToDate() ?: ""
                            onDateSelected(selectedDateLabel.value)
                        }
                    ) {
                        Text("OK", color = calendarPickerMainColor)
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            openDateDialog.value = false
                        }
                    ) {
                        Text("CANCEL", color = calendarPickerMainColor)
                    }
                }
            ) {
                DatePicker(
                    state = datePickerState,
                    colors = DatePickerDefaults.colors(
                        selectedDayContainerColor = calendarPickerMainColor,
                        selectedDayContentColor = Color.White,
                        selectedYearContainerColor = calendarPickerMainColor,
                        selectedYearContentColor = Color.White,
                        todayContentColor = calendarPickerMainColor,
                        todayDateBorderColor = calendarPickerMainColor
                    ),


                )
            }
        }
    }
}

fun Long.convertMillisToDate(): String {
    // Create a calendar instance in the default time zone
    val calendar = Calendar.getInstance().apply {
        timeInMillis = this@convertMillisToDate
        // Adjust for the time zone offset to get the correct local date
        val zoneOffset = get(Calendar.ZONE_OFFSET)
        val dstOffset = get(Calendar.DST_OFFSET)
        add(Calendar.MILLISECOND, -(zoneOffset + dstOffset))
    }
    // Format the calendar time in the specified format
    val sdf = SimpleDateFormat("dd MMM yyyy", Locale.US)
    return sdf.format(calendar.time)
}