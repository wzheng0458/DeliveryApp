package com.example.deliveryapp.delivery

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.deliveryapp.Screens
import com.example.deliveryapp.deliverydatabase.Address

@Composable
fun AddressListComponent(navController: NavController, addressViewModel: AddressViewModel, confirmDeliveryOrderViewModel: ConfirmDeliveryOrderViewModel, id: String?, selectedDate: String, selectedTime: String) {
    val addressList by addressViewModel.getListAddressById(id ?: "").observeAsState(emptyList())
    var selectedIndex by remember { mutableIntStateOf(-1) }
    var selectedAddress by remember { mutableStateOf<Address?>(null) }
    val showDialogConfirm = remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {

        Column {
            Text(
                text = "Where to?",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
                    .clickable {
                        navController.navigate(route = Screens.CreateNewAddressScreen.name + "/${id}")
                    },
                shape = RoundedCornerShape(8.dp),
                elevation = CardDefaults.cardElevation(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.5f))
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
                ) {
                    Text(
                        text = "Add Address",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.weight(1f)
                            .padding(end = 8.dp)
                    )
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            if (addressList.isNotEmpty()) {
                LazyColumn {
                    itemsIndexed(addressList) { index, item ->
                        AddressItem(
                            item = item,
                            navController = navController,
                            isChecked = selectedIndex == index,
                            onCheckedChange = {
                                selectedIndex = if (selectedIndex == index) -1 else index
                                selectedAddress = if (selectedIndex == index) item else null
                            },

                        )
                    }
                }
            } else {
                Text(
                    text = "You don't have any addresses. Please add one.",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    fontSize = 16.sp
                )
            }
        }

        val isButtonEnabled = if (selectedAddress != null && selectedDate.isNotEmpty() && selectedTime.isNotEmpty()) {
            true
        } else {
            false
        }

        Button(
            onClick = {
                showDialogConfirm.value = true
            },
            enabled = isButtonEnabled,
            colors = if (isButtonEnabled) {
                ButtonDefaults.buttonColors()
            } else {
                ButtonDefaults.buttonColors(
                    containerColor = Color.Gray.copy(alpha = 0.5f),
                    contentColor = Color.White.copy(alpha = 0.5f)
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Confirm Delivery Info")
        }
    }
    // AlertDialogs for confirmation
    if (showDialogConfirm.value) {
        AlertDialogBox(
            onDismissRequest = { showDialogConfirm.value = false },
            onConfirmation = {
                showDialogConfirm.value = false
                selectedAddress?.let {
                    confirmDeliveryOrderViewModel.insertOrder(selectedAddress, selectedDate, selectedTime)
//                    navController.navigate(route = Screens.DeliveryRecord.name + "/${id}")
                    Toast.makeText(context, "Successfully", Toast.LENGTH_LONG).show()
                }


            },
            dialogTitle = "Confirmation",
            dialogText = "Are you sure?",
            dialogIcon = Icons.Default.Person
        )
    }
}








@Composable
fun AddressItem(
    item: Address,
    navController: NavController,
    isChecked: Boolean,
    onCheckedChange: (Address) -> Unit,

) {


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
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
                    text = "Address: ${item.address}",
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
                        navController.navigate(route = Screens.MapScreenComponent.name + "/${item.id}" + "/${item.ownerId}" )
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
                Checkbox(
                    checked = isChecked,
                    onCheckedChange = { onCheckedChange(item) },
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        }
    }
}






