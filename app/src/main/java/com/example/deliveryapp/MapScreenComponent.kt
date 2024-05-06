package com.example.deliveryapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun MapScreenComponent(navController: NavController, viewModel: AddressViewModel, id: Int?, address: String?){
    var addressText by remember { mutableStateOf(address ?: "") }


    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = addressText,
            onValueChange = { addressText = it },
            label = { Text("New Address") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Button(
            onClick = {
                id?.let {
//                    viewModel.editAddress(id, addressText)
                }
                navController.navigate(route = Screens.DeliveryInfoUI.name)
            }) {
            Text("Save the changes")
        }
        Button(
            onClick = {
//                id?.let { viewModel.deleteAddress(id) }
                navController.navigate(route = Screens.DeliveryInfoUI.name)
            },
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text("Delete")
        }
    }

}