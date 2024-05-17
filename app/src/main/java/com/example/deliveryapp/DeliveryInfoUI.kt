package com.example.deliveryapp

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DeliveryInfoUI(
    navController: NavController,
    addressViewModel: AddressViewModel,
    confirmDeliveryOrderViewModel: ConfirmDeliveryOrderViewModel,
    id: String?
) {
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }
    var selectedDate by remember { mutableStateOf("") }
    var selectedTime by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ) {

            TabRow(selectedTabIndex = selectedTabIndex) {
                TabItems.forEachIndexed { index, item ->
                    Tab(
                        selected = index == selectedTabIndex,
                        onClick = {
                            selectedTabIndex = index
                        },
                        text = { Text(text = item.title) },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedTabIndex) {
                                    item.selectedIcon
                                } else
                                    item.unselectedIcon,
                                contentDescription = item.title
                            )
                        }
                    )
                }


            }

            when (selectedTabIndex) {
                0 -> {
                    Box {
                        DateTimePickerDialogComponent(
                            onDateSelected = { date -> selectedDate = date },
                            onTimeSelected = { time -> selectedTime = time }
                        )
                    }

                    AddressListComponent(navController = navController, addressViewModel, confirmDeliveryOrderViewModel,id, selectedDate, selectedTime)
                }
                1 -> {
                    DeliveryRecord(confirmDeliveryOrderViewModel = confirmDeliveryOrderViewModel, id = id)

                }

            }
        }
    }




