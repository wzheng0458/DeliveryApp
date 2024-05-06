package com.example.deliveryapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun AddressListComponent(navController: NavController, viewModel: AddressViewModel) {

//    val addressList by viewModel.addressList.observeAsState()
    var inputAddress by remember {
        mutableStateOf("")
    }
    // Generate ID for new address
//    val nextId = addressList?.maxByOrNull { it.id }?.id?.plus(1) ?: 1

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp)
    ) {
        Text(
            text = "Where to?",
            style = MaterialTheme.typography.titleSmall,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .weight(1f),
                value = inputAddress,
                label = { Text("Enter your address") },
                onValueChange = {
                    inputAddress = it
                }
            )
            Button(
                onClick = {
//                    viewModel.addAddress(inputAddress)
                    inputAddress = ""
                }
            ) {
                Text(text = "Add New")
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

//        addressList?.let {
//            LazyColumn(
//                modifier = Modifier.weight(1f),
//                content = {
//                    itemsIndexed(it) { index: Int, item: Address ->
//                        AddressItem(item = item, navController = navController)
////
//                    }
//                }
//            )
//        } ?: Text(
//            modifier = Modifier.fillMaxWidth(),
//            textAlign = TextAlign.Center,
//            text = "You don't have any addresses. Please add one.",
//            fontSize = 16.sp
//        )
//    }
    }

    @Composable
    fun AddressItem(item: Address, navController: NavController) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
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
                        text = "ID: ${item.id}",
                        fontSize = 14.sp,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Text(
                        text = "Address: ${item.address}",
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                IconButton(
                    onClick = {
                        navController.navigate(route = Screens.MapScreenComponent.name + "/${item.id}" + "/${item.address}")
                    },
                    modifier = Modifier.padding(8.dp),

                    ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit",
                        tint = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }

    }
}




