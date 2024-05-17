package com.example.deliveryapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController


@Composable
fun NavigationController(){
    val navController = rememberNavController()
    val addressViewModel = AddressViewModel()
    val customerViewModel = CustomerViewModel()
    val confirmDeliveryOrderViewModel = ConfirmDeliveryOrderViewModel()

    Scaffold (
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by  navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                listOfNavItems.forEach {navItem ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any{ it.route == navItem.route } == true,
                        onClick = {
                                  navController.navigate(navItem.route){
                                      popUpTo(navController.graph.findStartDestination().id){
                                          saveState = true
                                      }
                                      launchSingleTop = true
                                      restoreState = true
                                  }
                        },
                        icon = { 
                            Icon(
                                imageVector = navItem.icon,
                                contentDescription = null
                            )
                        },
                        label = {
                            Text(text = navItem.label)
                        }
                    )
                }
            }
        }
    ) { paddingValues ->
        NavHost(navController = navController,
            startDestination = Screens.Home.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = Screens.Home.name) {
                Home(navController, customerViewModel)
            }
            composable(route = Screens.DeliveryInfoUI.name + "/{id}" ) {
                val id = it.arguments?.getString("id")

                DeliveryInfoUI(navController, addressViewModel, confirmDeliveryOrderViewModel, id)

            }

            composable(route = Screens.MapScreenComponent.name + "/{id}" + "/{custId}") {
                val id = it.arguments?.getString("id")?.toInt()
                val custId = it.arguments?.getString("custId")
                MapScreenComponent(navController, addressViewModel, id, custId)
            }
            composable(route = Screens.CreateNewAddressScreen.name + "/{id}") {
                val id = it.arguments?.getString("id")
                CreateNewAddressScreen(navController, addressViewModel, id)
            }
            composable(route = Screens.DeliveryRecord.name + "/{id}" ) {
                val id = it.arguments?.getString("id")
                DeliveryRecord(confirmDeliveryOrderViewModel, id)
            }

        }
    }

}
