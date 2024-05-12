package com.example.deliveryapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun Home(navController: NavController, customerViewModel: CustomerViewModel){

//    val context = LocalContext.current
//    val scope = rememberCoroutineScope()
//    val customerAddressListViewModel: CustomerAddressListViewModel = viewModel(
//        factory = CustomerAddressViewModelFactory(context.applicationContext as Application)
//    )

    var customerId by remember { mutableStateOf("") }




    Column (
        modifier = Modifier.
        fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = customerId,
            onValueChange = { customerId = it },
            label = { Text(text = "Enter customerId ") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.8f)
        )
        Button(
            onClick = {

                customerViewModel.addCustomer("C001", "User 1", "user1@email.com","123-456-789")
                navController.navigate(route = Screens.DeliveryInfoUI.name + "/${customerId}")
            }
        ) {
            Text(text = "New Customer")
        }
    }

}