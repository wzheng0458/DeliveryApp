package com.example.deliveryapp

import android.app.TimePickerDialog
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateTimePickerDialogComponent() {
    // Initial state setup for the DatePickerDialog. Specifies to show the picker initially
    val datePickerState = rememberDatePickerState(initialDisplayMode = DisplayMode.Picker)
    // State to hold the selected date as a String
    val selectedDateLabel = remember { mutableStateOf("") }
    // State to control the visibility of the DatePickerDialog
    val openDialog = remember { mutableStateOf(false) }
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
            time = "$hoursOfDay: $minutes"
        }, hour, minute, false
    )

    // Layout for displaying the button and the selected date
    Column(
        modifier = Modifier
            .padding(8.dp),
    ) {
        // Button to open the DatePickerDialog
        Text(
            text = "When ?",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            onClick = {
                openDialog.value = !openDialog.value
            }
        ) {
            Text("Open Date Picker")
        }

        Button(
            onClick = {
                timePickerDialog.show()
            }
        ){
            Text(text = "Show Time Picker")
        }
        Text(
            text ="Selected Date and Time:${selectedDateLabel.value} ${time}"
        )
    }

    // Conditional display of the DatePickerDialog based on the openDialog state
    if (openDialog.value) {
        // DatePickerDialog component with custom colors and button behaviors
        DatePickerDialog(
            colors = DatePickerDefaults.colors(
                containerColor = Color(0xFFF5F0FF),
            ),
            onDismissRequest = {
                // Action when the dialog is dismissed without selecting a date
                openDialog.value = false
            },
            confirmButton = {
                // Confirm button with custom action and styling
                TextButton(
                    onClick = {
                        // Action to set the selected date and close the dialog
                        openDialog.value = false
                        selectedDateLabel.value =
                            datePickerState.selectedDateMillis?.convertMillisToDate() ?: ""
                    }
                ) {
                    Text("OK", color = calendarPickerMainColor)
                }
            },
            dismissButton = {
                // Dismiss button to close the dialog without selecting a date
                TextButton(
                    onClick = {
                        openDialog.value = false
                    }
                ) {
                    Text("CANCEL", color = calendarPickerMainColor)
                }
            }
        ) {
            // The actual DatePicker component within the dialog
            DatePicker(
                state = datePickerState,
                colors = DatePickerDefaults.colors(
                    selectedDayContainerColor = calendarPickerMainColor,
                    selectedDayContentColor = Color.White,
                    selectedYearContainerColor = calendarPickerMainColor,
                    selectedYearContentColor = Color.White,
                    todayContentColor = calendarPickerMainColor,
                    todayDateBorderColor = calendarPickerMainColor
                )
            )
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
    val sdf = SimpleDateFormat("dd MMM yyyy,", Locale.US)
    return sdf.format(calendar.time)
}