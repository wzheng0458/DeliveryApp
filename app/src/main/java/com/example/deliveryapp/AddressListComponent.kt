package com.example.deliveryapp


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AddressListComponent(viewModel: AddressViewModel, navController: NavController){

    val addressList by viewModel.addressList.observeAsState()
    var inputAddress by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(
                value = inputAddress,
                label = { Text("Enter your address") },
                onValueChange = {
                    inputAddress = it
                })
            Button(
                onClick = {
                    viewModel.addAddress(inputAddress)
                    inputAddress = ""
                }) {
                Text(text = "Add New")

            }
        }
        addressList?.let {
            LazyColumn(
                content = {
                    itemsIndexed(it) { index: Int, item: Address ->
                        AddressItem(item = item, navController = navController)
                    }

                }
            )
        }?: Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "You don't have an address. Please add it",
            fontSize = 16.sp
        )

    }
}



@Composable
fun AddressItem(item : Address, navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column (
            modifier = Modifier.weight(1f)
        ){
            Text(
                text = item.id.toString(),
                fontSize = 10.sp,
                color = Color.White
            )
            Text(
                text = item.address,
                fontSize = 10.sp,
                color = Color.White
            )
        }
        TextButton(onClick = {
            navController.navigate(Routes.MapScreenComponent +"${item.id}" + "/${item.address}")
        }) {
            Text(
                text = "Edit",
                fontSize = 10.sp,
                color = Color.White
            )
        }
    }
}


