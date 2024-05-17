package com.example.deliveryapp

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun CreateNewAddressScreen(navController: NavController, addressViewModel: AddressViewModel, ownerId: String?) {
    var addressText by remember { mutableStateOf( "") }
    var unit by remember { mutableStateOf("") }
    var desc by remember { mutableStateOf("") }
    var state by remember { mutableStateOf("") }
    val context = LocalContext.current
    var selectedOption by remember { mutableStateOf<Option?>(null) }
    val showDialogSave = remember { mutableStateOf(false) }
    val showDialogDelete = remember { mutableStateOf(false) }


    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            shape = RoundedCornerShape(2.dp),
            elevation = CardDefaults.cardElevation(18.dp),


            ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Let us know your location",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )

                OutlinedTextField(
                    value = addressText,
                    onValueChange = { addressText = it },
                    label = { Text("New Address") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
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
                        Text("Meet at door", style = MaterialTheme.typography.labelSmall)
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
                        Text("Meet Outside", style = MaterialTheme.typography.labelSmall )
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
                    state = dropDownMenu(
                        modifier = Modifier
                            .weight(2.5f)
                    )
                }


                // Description text field
                OutlinedTextField(
                    value = desc,
                    onValueChange = { desc = it },
                    label = { Text("Description(optional)") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    maxLines = 3 // Adjust the number of lines as needed
                )

                // Buttons
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    val isButtonEnabled = if (addressText.isNotEmpty()  && unit.isNotEmpty() && state.isNotEmpty() && selectedOption != null) {
                        true
                    } else {
                        false
                    }

                    Button(
                        onClick = {
                            showDialogSave.value = true
                        },
                        enabled = isButtonEnabled,
                        colors = if (isButtonEnabled) {
                            ButtonDefaults.buttonColors()
                        } else {
                            ButtonDefaults.buttonColors(
                                containerColor = Color.Gray.copy(alpha = 0.5f),
                                contentColor = Color.White.copy(alpha = 0.5f)
                            )
                        }
                    ) {
                        Text("Create")
                    }

                    Button(
                        onClick = {
                            showDialogDelete.value = true

                        },
                        colors = ButtonDefaults.buttonColors(Color.Red)
                    ) {
                        Text("Cancel")
                    }
                }

                // AlertDialogs for confirmation
                if (showDialogSave.value) {
                    AlertDialogBox(
                        onDismissRequest = { showDialogSave.value = false },
                        onConfirmation = {
                            showDialogSave.value = false
                            navController.navigate(route = Screens.DeliveryInfoUI.name + "/${ownerId}")
                            addressViewModel.addAddress(address = addressText,
                                meetOption = selectedOption?.name ?: "MEET_AT_DOOR",
                                unit = unit,
                                state = state,
                                desc = desc,
                                customerId = ownerId?: ""
                            )
                            Toast.makeText(context, "Successfully", Toast.LENGTH_LONG).show()
                        },
                        dialogTitle = "Confirmation",
                        dialogText = "Are you sure?",
                        dialogIcon = Icons.Default.Person
                    )
                }

                if (showDialogDelete.value) {
                    AlertDialogBox(
                        onDismissRequest = { showDialogDelete.value = false },
                        onConfirmation = {
                            showDialogDelete.value = false
                            navController.navigate(route = Screens.DeliveryInfoUI.name + "/${ownerId}")

//                        customerAddressListViewModel.deAddress(newAddress)
                            Toast.makeText(context, "Successfully", Toast.LENGTH_LONG).show()
                        },
                        dialogTitle = "Confirmation",
                        dialogText = "Are you sure?",
                        dialogIcon = Icons.Default.Person
                    )
                }
            }
        }

    }

}

