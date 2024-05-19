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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.deliveryapp.booking.BookingApp
import com.example.deliveryapp.delivery.AddressViewModel
import com.example.deliveryapp.delivery.ConfirmDeliveryOrderViewModel
import com.example.deliveryapp.delivery.CreateNewAddressScreen
import com.example.deliveryapp.delivery.DeliveryInfoUI
import com.example.deliveryapp.delivery.DeliveryRecord
import com.example.deliveryapp.delivery.MapScreenComponent
import com.example.deliveryapp.login.AccountViewModel
import com.example.deliveryapp.login.AddAccountScreen
import com.example.deliveryapp.login.CustomerMainScreen
import com.example.deliveryapp.login.LoginMainScreen
import com.example.deliveryapp.login.LoginViewModel
import com.example.deliveryapp.login.ViewAccountScreen
import com.example.deliveryapp.logindatabase.AccountState
import com.example.loginapp.RegisterScreen
import com.example.loginapp.StaffMainScreen

import com.example.loginapp.ui.theme.RegisterViewModel


@Composable
fun NavigationController(){
    val navController = rememberNavController()
    val addressViewModel = AddressViewModel()
    val confirmDeliveryOrderViewModel = ConfirmDeliveryOrderViewModel()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold (
        bottomBar = {
            if (currentDestination?.route != Screens.LoginMainScreen.name &&
                currentDestination?.route != Screens.RegisterScreen.name &&
                currentDestination?.route != Screens.StaffMainScreen.name &&
                currentDestination?.route != Screens.ViewAccountScreen.name &&
                currentDestination?.route != Screens.StaffMainScreen.name) {
                NavigationBar {
                    listOfNavItems.forEach { navItem ->
                        NavigationBarItem(
                            selected = currentDestination?.hierarchy?.any { it.route == navItem.route } == true,
                            onClick = {
                                navController.navigate(navItem.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
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
        }
    ) { paddingValues ->
        NavHost(navController = navController,
            startDestination = Screens.LoginMainScreen.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            //login module start
            composable(route = Screens.LoginMainScreen.name){
                LoginMainScreen(navController = navController,viewModel = viewModel(LoginViewModel::class.java))
            }
            composable(route = Screens.RegisterScreen.name){
                RegisterScreen(
                    navController = navController, viewModel = viewModel(
                        RegisterViewModel::class.java
                    )
                )
            }

            composable(route = Screens.StaffMainScreen.name + "/{staffId}"){
                val staffId = it.arguments?.getString("staffId")

                StaffMainScreen(navController = navController, staffId)
            }

            composable(route = Screens.CustomerMainScreen.name+ "/{custId}"){
                val custId = it.arguments?.getString("custId")

                CustomerMainScreen(navController = navController, custId)
            }

            composable(route = Screens.ViewAccountScreen.name) {
                val viewModel: AccountViewModel = viewModel()
                ViewAccountScreen(navController = navController, onEvent = viewModel::onEvent, state = AccountState(), viewModel = viewModel)
            }
            composable(route = Screens.AddAccountScreen.name) {
                val viewModel: AccountViewModel = viewModel()
                AddAccountScreen(navController = navController, onEvent = viewModel::onEvent, state = AccountState(), viewModel = viewModel)
            }

            //login module end

            //delivery module start

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
                DeliveryRecord(navController ,confirmDeliveryOrderViewModel, id)
            }
            //delivery module end

            //Booking module start
            composable(route = Screens.BookingApp.name + "/{id}" ) {
                val id = it.arguments?.getString("id")
                BookingApp(navController, id)
            }
            composable(route = Screens.BookingScreen.name + "/{id}" ) {
                val id = it.arguments?.getString("id")
                BookingApp(navController, id)
            }

            //Booking module end


        }
    }

}
