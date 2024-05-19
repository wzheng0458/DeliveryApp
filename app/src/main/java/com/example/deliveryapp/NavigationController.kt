package com.example.deliveryapp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.example.deliveryapp.order.Bill
import com.example.deliveryapp.order.CartView
import com.example.deliveryapp.order.Order
import com.example.deliveryapp.order.OrderMenu
import com.example.deliveryapp.orderdatabase.CartViewModel
//import com.example.deliveryapp.orderdatabase.OrderViewModel
import com.example.loginapp.RegisterScreen
import com.example.loginapp.StaffMainScreen
import com.example.loginapp.ui.theme.RegisterViewModel



@Composable
fun NavigationController(){
    val navController = rememberNavController()
    val addressViewModel = AddressViewModel()
    val confirmDeliveryOrderViewModel = ConfirmDeliveryOrderViewModel()
    val cartViewModel = viewModel<CartViewModel>()

//    val orderViewModel = viewModel<OrderViewModel>()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold (
        bottomBar = {
            if (currentDestination?.route != Screens.LoginMainScreen.name &&
                currentDestination?.route != Screens.RegisterScreen.name &&
                currentDestination?.route != Screens.StaffMainScreen.name+ "/{staffId}" &&
                currentDestination?.route != Screens.ViewAccountScreen.name
                ) {
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

            //Order module start
            composable(route = Screens.OrderMenu.name + "/{id}" ) {
                val id = it.arguments?.getString("id")


                OrderMenu(navController, id, 0)

            }
            //if i navigate from delivery module
            composable(route = Screens.OrderMenu.name + "/{id}/{isDeliveryOrder}" ) {
                val id = it.arguments?.getString("id")
                var isDeliveryOrder = it.arguments?.getString("isDeliveryOrder")?.toInt()
                if (isDeliveryOrder == 1){
                    isDeliveryOrder = 1
                }else
                    isDeliveryOrder = 0

                OrderMenu(navController, id, isDeliveryOrder)

            }
            composable(route = Screens.Order.name + "/{category}/{id}/{isDeliveryOrder}" ) {
                val category = it.arguments?.getString("category") ?: ""
                val id = it.arguments?.getString("id") ?: ""
                var isDeliveryOrder = it.arguments?.getString("isDeliveryOrder")?.toInt()


                Order(navController, category, cartViewModel, id, isDeliveryOrder)
            }
            composable(route = Screens.CartView.name + "/{id}/{isDeliveryOrder}") {
                val id = it.arguments?.getString("id") ?: ""
                var isDeliveryOrder = it.arguments?.getString("isDeliveryOrder")?.toInt()


                CartView(navController, cartViewModel, id, isDeliveryOrder)
            }
            composable(route = Screens.Bill.name + "/{id}/{isDeliveryOrder}" ) {
                val id = it.arguments?.getString("id") ?: ""
                var isDeliveryOrder = it.arguments?.getString("isDeliveryOrder")?.toInt()

                Bill(cartViewModel, navController, id, confirmDeliveryOrderViewModel, isDeliveryOrder)
            }
            //Order module end


        }
    }

}
