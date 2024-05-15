package com.example.deliveryapp

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun MapScreenComponent(navController: NavController, address: String?, customerAddressListViewModel: CustomerAddressListViewModel){
    var addressText by remember { mutableStateOf(address ?: "") }
    var unit by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }

    val context = LocalContext.current
    var selectedOption by remember { mutableStateOf<Option?>(null) }
    val showDialogSave = remember { mutableStateOf(false) }
    val showDialogDelete = remember { mutableStateOf(false) }
//    val editAddress = Address(
//        address = addressText,
//        meetOption = selectedOption?.name ?: "Meet at door",
//        unit = unit,
//        state = state,
//        desc = desc,
//        ownerId = address.owner,
//        createdAt = Date.from(Instant.now())
//    )

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Let us know your location",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(16.dp)
        )
        OutlinedTextField(
            value = addressText,
            onValueChange = { addressText = it },
            label = { Text("New Address") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .background(
                            if (selectedOption == Option.MEET_AT_DOOR) MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.2f
                            ) else Color.Transparent
                        )
                        .padding(4.dp)
                ) {
                    IconToggleButton(
                        checked = selectedOption == Option.MEET_AT_DOOR,
                        onCheckedChange = {
                            selectedOption = if (it) Option.MEET_AT_DOOR else null
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_dooropen),
                            contentDescription = "Meet at door",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                Text("Leave at door", style = MaterialTheme.typography.labelSmall)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .background(
                            if (selectedOption == Option.LEAVE_AT_DOOR) MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.2f
                            ) else Color.Transparent
                        )
                        .padding(4.dp)
                ) {
                    IconToggleButton(
                        checked = selectedOption == Option.LEAVE_AT_DOOR,
                        onCheckedChange = {
                            selectedOption = if (it) Option.LEAVE_AT_DOOR else null
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_doorclose),
                            contentDescription = "Leave at door",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                Text("Leave at door", style = MaterialTheme.typography.labelSmall)
            }

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .background(
                            if (selectedOption == Option.MEET_OUTSIDE) MaterialTheme.colorScheme.primary.copy(
                                alpha = 0.2f
                            ) else Color.Transparent
                        )
                        .padding(4.dp)
                ) {
                    IconToggleButton(
                        checked = selectedOption == Option.MEET_OUTSIDE,
                        onCheckedChange = {
                            selectedOption = if (it) Option.MEET_OUTSIDE else null
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_outside),
                            contentDescription = "Meet Outside",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                Text("Leave at door", style = MaterialTheme.typography.labelSmall )
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            // Unit text field
            OutlinedTextField(
                value = unit,
                onValueChange = { unit = it },
                label = { Text("Unit/Floor") },
                modifier = Modifier
                    .weight(1.5f) // Occupies one-third of the width
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            )

            // Dropdown menu
          state =  DropDownMenu(
                modifier = Modifier
                    .weight(2.5f)
             )
        }

        OutlinedTextField(
            value = desc,
            onValueChange = { desc = it },
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .height(100.dp)
        )
        Button(
            onClick = {
                showDialogSave.value = true
            }) {
            Text("Save the changes")
        }
        Button(
            onClick = {
                showDialogDelete.value = true
            },
            colors = ButtonDefaults.buttonColors(Color.Red)
        ) {
            Text("Delete")
        }
        if (showDialogSave.value) {
            AlertDialogBox(
                onDismissRequest = { showDialogSave.value = false },
                onConfirmation = {
                    showDialogSave.value = false
//                    customerAddressListViewModel.editAddress(editAddress)
                    Toast.makeText(context, "Successfully", Toast.LENGTH_LONG).show()
                    navController.navigate(route = Screens.DeliveryInfoUI.name + "/${address}")
                },
                dialogTitle = "Confirmation",
                dialogText = "Are you sure?",
                dialogIcon = Icons.Default.Person
            )
        }
        if(showDialogDelete.value){
            AlertDialogBox(
                onDismissRequest = { showDialogDelete.value = false },
                onConfirmation = {
//                    id?.let {
//                        showDialogDelete.value = false
//                        customerAddressListViewModel.deleteAddress(id)
//                        navController.navigate(route = Screens.DeliveryInfoUI.name + "/${id}")
//                    }


                    Toast.makeText(context, "Successfully", Toast.LENGTH_LONG).show()
                },
                dialogTitle = "Confirmation",
                dialogText = "Are you sure?",
                dialogIcon = Icons.Default.Person
            )

        }
    }


}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(modifier : Modifier): String {

    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Johor", "Kedah", "Kelantan", "Kuala Lumpur", "Melacca", "Negeri Sembilan", "Pahang", "Perlis", "Penang", "Perak", "Sabah", "Sarawak", "Selangor")
    var selectedText by remember { mutableStateOf(suggestions[0]) }

    Column(modifier) {
        ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = {expanded = !expanded}) {
            TextField(
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                value = selectedText,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)}
            )
            ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = true }) {
                suggestions.forEachIndexed{ index, text ->
                    DropdownMenuItem(
                        text = { Text(text = text)},
                        onClick = {
                            selectedText = suggestions[index]
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                    )
                }
            }
        }
    }
    return selectedText
}
enum class Option(val optionString: String) {
    MEET_AT_DOOR("Meet at door"),
    LEAVE_AT_DOOR("Leave at door"),
    MEET_OUTSIDE("Meet outside")
}