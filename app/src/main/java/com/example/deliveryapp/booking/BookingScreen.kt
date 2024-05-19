package com.example.deliveryapp.booking

import android.app.TimePickerDialog
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.deliveryapp.R
import com.example.deliveryapp.Screens
import com.example.deliveryapp.bookingdatabase.Booking
import kotlinx.coroutines.delay
import java.sql.Time
import java.util.Calendar
import java.util.Date

val TableNum= listOf("T01","T02","T03","T04","T05","T06","T07","T08","T09","T10")
@Composable
fun BookingScreen(
    modifier: Modifier = Modifier,
    onSelected: () -> Unit,
    ViewModel: ViewModel,
    Book: BookingViewModel,
    Time: TimeViewModel,
    id: String?,
    navController: NavController
) {
    var date = remember { mutableStateOf("") }
    var StartTimeChoose = remember { mutableStateOf("") }
    var EndTimeChoose = remember { mutableStateOf("") }
    val showDialog = remember { mutableStateOf(false) }
    val result = validaion(
        Stime = StartTimeChoose.value,
        Etime = EndTimeChoose.value,
        time = Time,
        Date = date.value
    )
    val DataNotNull =
        if (date.value.isNotEmpty() && StartTimeChoose.value.isNotEmpty() && EndTimeChoose.value.isNotEmpty()) {
            true
        } else {
            false
        }




    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Image(
                painter = painterResource(R.drawable.restaurant_image),
                contentDescription = "Seat",
                modifier = Modifier.aspectRatio(16f / 9f)
            )
        }
        Column {
            date.value = datepicker(date, Time)
            StartTimeChoose.value = timepicker(StartTimeChoose, date, TimeViewModel())
            Spacer(modifier = Modifier)
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "To", fontSize = 20.sp)
            }
            EndTimeChoose.value = timepicker(EndTimeChoose, date, TimeViewModel())


        }
        Row() {

            OutlinedButton(
                modifier = Modifier.weight(1f),
//                result &&
                enabled =  DataNotNull,
                onClick = {

                    showDialog.value = true;
                }
            ) {

                Text(text = "Check")
            }
        }
    }
    if (showDialog.value) {
        TableDialog(
            tableNum = TableNum,
            setShowDialog = { showDialog.value = it },
            ViewModel = ViewModel,
            onSelected,
            Book,
            StartTimeChoose.value,
            EndTimeChoose.value,
            date.value,
            id,
            navController
        )
    }

}


@Composable
fun datepicker(date: MutableState<String>, ViewModel: TimeViewModel): String {
    val Context = LocalContext.current
    val calendar = Calendar.getInstance()
    val year: Int = calendar.get(Calendar.YEAR)
    val month: Int = calendar.get(Calendar.MONTH)
    val day: Int = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()


    val DatePickerDialog = android.app.DatePickerDialog(
        Context,
        { DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/${month + 1}/$year"
        },
        year, month, day,
    );
    DatePickerDialog.datePicker.minDate = calendar.timeInMillis

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        OutlinedButton(
            onClick =
            { DatePickerDialog.show() },
            colors = ButtonDefaults.buttonColors(Red)
        ) {
            Text(text = "Date", color = Color.White)
        }
        Text(text = "Selected Date:${date.value}")
    }
    return date.value

}

@Composable
fun timepicker(time: MutableState<String>, date: MutableState<String>, ViewModel: TimeViewModel): String {
    val calendar = Calendar.getInstance()
    var hour: Int = calendar.get(Calendar.HOUR_OF_DAY)
    var minute: Int = calendar.get(Calendar.MINUTE)
    val Context = LocalContext.current


    val TimePickerDialog = TimePickerDialog(
        Context,
        { TimePicker, hour: Int, minute: Int ->

            time.value = ViewModel.TimeCombination(hour, minute)


        }, hour, minute, false
    )





    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        OutlinedButton(
            onClick = { TimePickerDialog.show() },
            colors = ButtonDefaults.buttonColors(Red)
        ) {
            Text(text = "Choose the time", color = Color.White)

        }


        Text(text = "Selected Time: ${time.value}", fontSize = 30.sp)

    }
    return time.value
}

@Composable
fun TableDialog(
    tableNum: List<String>,
    setShowDialog: (Boolean) -> Unit,
    ViewModel: ViewModel,
    onSelected: () -> Unit,
    Book: BookingViewModel,
    StartTime: String,
    EndTime: String, // Corrected typo from EndTIme to EndTime
    Date: String,
    id: String?,
    navController: NavController
) {
    var selectedValue = remember { mutableStateOf("") }
    val isButtonEnabled = selectedValue.value.isNotEmpty()

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.padding(16.dp)

        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Table Number which is available")

                Column(modifier = Modifier.padding(8.dp)) {
                    tableNum.forEach { item ->
                        Row(
                            modifier = Modifier
                                .selectable(
                                    selected = selectedValue.value == item,
                                    onClick = { selectedValue.value = item }
                                )

                        ) {
                            RadioButton(
                                selected = selectedValue.value == item,
                                onClick = { selectedValue.value = item }
                            )
                            Text(item)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = {
                        ViewModel.BookingIDgenerate(
                            selectedValue.value,
                            StartTime,
                            EndTime,
                            Date
                        )
                        Book.addBooking(
                            id ?: "",
                            "Booked",
                            Date,
                            StartTime,
                            EndTime,
                            selectedValue.value,
                            ViewModel.BookingID.value
                        )
                        onSelected()
                        setShowDialog(false)
                    },
                    enabled = isButtonEnabled,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = "Confirm")
                }

                Spacer(modifier = Modifier.height(8.dp))

                Button(
                    onClick = { setShowDialog(false) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
                ) {
                    Text(text = "Cancel")
                }
            }
        }
    }
}

@Composable
fun validaion(Stime: String, Etime: String, time: TimeViewModel, Date: String): Boolean {
    val valid: Int = time.TimeValidate(Stime, Etime, Date)
    val Context = LocalContext.current
    var result = true
    var message: String = ""

    if (valid == 2) {
        message = "Please book a time slot at least 30 minutes or at most 2 hour"
        result = false
    } else if (valid == 1) {
        message = "Please book the time slot that is 30 minutes late than the current time"
        result = false
    } else if (valid == 3) {
        message = "Please book the time slot within our operation hour"
        result = false
    }


    if (result == false) {
        Toast.makeText(
            Context,
            message,
            Toast.LENGTH_SHORT
        ).show()
    }
    return result
}