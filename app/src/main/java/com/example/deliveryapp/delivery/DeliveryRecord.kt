package com.example.deliveryapp.delivery

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.deliveryapp.Screens
import com.example.deliveryapp.deliverydatabase.ConfirmDeliveryOrder

@Composable
fun DeliveryRecord(navController: NavController,confirmDeliveryOrderViewModel: ConfirmDeliveryOrderViewModel, id: String?){
    val deliveryOrder by confirmDeliveryOrderViewModel.getListOrderById(id ?: "").observeAsState(emptyList())


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(deliveryOrder) { index, order ->
            DeliveryOrderItem(
                navController,
                order,
                confirmDeliveryOrderViewModel,
                order.id,

            )
        }
    }
}

@Composable
fun DeliveryOrderItem(navController: NavController ,order: ConfirmDeliveryOrder, confirmDeliveryOrderViewModel: ConfirmDeliveryOrderViewModel, index: Int?){
    val context = LocalContext.current
    var isDeliveryOrder by remember {
        mutableIntStateOf(0)
    }
    val showDialogDelete = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                isDeliveryOrder = 1
                navController.navigate(Screens.OrderMenu.name + "/${order.id}/${isDeliveryOrder}")
            },
        shape = RoundedCornerShape(2.dp),
        elevation = CardDefaults.cardElevation(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFA500))


    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Column(
                modifier = Modifier.padding(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = "Address: ${order.deliveryAddress}",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

                Text(
                    text = "Date and Time: ${order.deliveryDate} at ${order.deliveryTime}",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onSurface
                )

            }
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .wrapContentSize()
                    .padding(8.dp)
            ) {
                IconButton(
                    onClick = {
                        index?.let {
                            showDialogDelete.value = true
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Edit",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
    if (showDialogDelete.value) {
        AlertDialogBox(
            onDismissRequest = { showDialogDelete.value = false },
            onConfirmation = {
                index?.let {
                    showDialogDelete.value = false

                    confirmDeliveryOrderViewModel.deleteOrder(index)
//                            navController.navigate(Screens.DeliveryInfoUI.name + "/${order.id}/${isDeliveryOrder}")
                    Toast.makeText(context, "Successfully", Toast.LENGTH_LONG).show()
                }

            },
            dialogTitle = "Confirmation",
            dialogText = "Are you sure?",
            dialogIcon = Icons.Default.Person
        )
    }
}

