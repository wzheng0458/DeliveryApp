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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
                order.id
            )
        }
    }
}

@Composable
fun DeliveryOrderItem(navController: NavController ,order: ConfirmDeliveryOrder, confirmDeliveryOrderViewModel: ConfirmDeliveryOrderViewModel, index: Int?){
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { /*navigate to order*/ },
        shape = RoundedCornerShape(2.dp),
        elevation = CardDefaults.cardElevation(10.dp)


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
                            confirmDeliveryOrderViewModel.deleteOrder(index)
                            navController.navigate(Screens.DeliveryInfoUI.name + "/${order.id}")
                            Toast.makeText(context, "Successfully", Toast.LENGTH_LONG).show()
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
}

