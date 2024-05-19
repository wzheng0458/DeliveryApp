package com.example.deliveryapp.booking


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialogDefaults.shape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

import com.example.deliveryapp.bookingdatabase.Booking

import kotlinx.coroutines.delay
import kotlin.reflect.KFunction1

@Composable
fun RecordScreen(BookingViewModel:BookingViewModel, id:String?) {
    val bookinglist by BookingViewModel.printBookingbyName(id?: "").observeAsState(emptyList())
    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxSize(),

        contentPadding = PaddingValues(vertical = 12.dp),
    ) {
        items(bookinglist)
        {booking ->
            BookingItem(BookingViewModel,booking, id)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookingItem(
    ViewModel:BookingViewModel,
    BookingRecord: Booking,
    id:String?
) {
    val currentItem by rememberUpdatedState(BookingRecord)
    val showDialog = remember { mutableStateOf(false) }
    val context = LocalContext.current
    var show by remember { mutableStateOf(true) }
    val dismissState = rememberDismissState(
        confirmValueChange = {
            if (it == DismissValue.DismissedToStart) {
                show = false
                true
            } else false
        }, positionalThreshold = { 150.dp.toPx() }
    )
    if (showDialog.value) {
        ConfirmDialog(
            setShowDialog = { showDialog.value = it },
            Booking = BookingRecord,
            ViewModel
        )
    }

    AnimatedVisibility(
        show, exit = fadeOut(spring())
    ) {
        SwipeToDismiss(
            state = dismissState,
            modifier = Modifier,
            background = {
                DeleteBackground(dismissState)
            },
            dismissContent = {
                BookingLayout(BookingRecord,id)
            }
        )
    }

    LaunchedEffect(show) {
        if (!show) {
            delay(20)
            showDialog.value = true
        }
    }
}

@Composable
fun BookingLayout(Booking: Booking, id:String?) {
    var expandedState by remember { mutableStateOf(false) }
    val rotationState = if (expandedState) 180f else 0f


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = tween(
                    durationMillis = 300,
                    easing = LinearOutSlowInEasing
                )
            ),
        shape = shape,
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {


                Text(
                    modifier = Modifier
                        .weight(6f),
                    text = Booking.BookingId,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .alpha(0.2f)
                        .rotate(rotationState),
                    onClick = {
                        expandedState = !expandedState
                    }) {
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Drop-Down Arrow"
                    )
                }
            }

            if (expandedState) {
                Text(
                    text = "Table Number:${Booking.TableNum}\n" + "Date:${Booking.Date}\n" +
                            "Time:${Booking.StartTime}" + "-" + "${Booking.EndtTime}",
                    overflow = TextOverflow.Ellipsis
                )
                Text(text = "${Booking.State}", color = Color.Green)

            }
        }
    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteBackground(
    swipeDismissState: DismissState
) {
    val color = if (swipeDismissState.dismissDirection == DismissDirection.EndToStart) {
        Color.Red
    } else Color.Transparent

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            imageVector = Icons.Default.Delete,
            contentDescription = null,
            tint = Color.White
        )
    }
}

@Composable
fun ConfirmDialog(
    setShowDialog: (Boolean) -> Unit,
    Booking: Booking,
    viewModel: BookingViewModel
) {
    val context = LocalContext.current

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = Color.White
        ) {
            Column(

            ) {
                Column(
                    modifier = Modifier
                        .padding(20.dp)
                        .fillMaxWidth(),
                ) {


                    Text(text = "Do you want to delete the Record:${Booking.BookingId}?")
                    Spacer(modifier = Modifier.height(20.dp))

                }
                Spacer(modifier = Modifier.height(20.dp))

                Column(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {
                            viewModel.deleteBooking(Booking.BookingId)
                            Toast.makeText(context, "Record ${Booking.BookingId} deleted successfully", Toast.LENGTH_SHORT).show()
                            setShowDialog(false)


                        },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "Yes")
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Button(
                        onClick = {

                        },
                        shape = RoundedCornerShape(50.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp)
                    ) {
                        Text(text = "No")

                    }
                    Spacer(modifier = Modifier.height(20.dp))



                }

            }
        }
    }
}