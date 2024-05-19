package com.example.deliveryapp


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeliveryDining
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material.icons.filled.TableRestaurant
import androidx.compose.ui.graphics.vector.ImageVector

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

val listOfNavItems: List<NavItem> = listOf(
    NavItem(
        label = "Home",
        icon = Icons.Default.Home,
        route = Screens.CustomerMainScreen.name + "/{custId}"
    ),
    NavItem(
        label = "Order",
        icon = Icons.Default.Restaurant,
        route = Screens.OrderMenu.name + "/{id}"
    ),
    NavItem(
        label = "Booking",
        icon = Icons.Default.TableRestaurant,
        route = Screens.BookingApp.name + "/{id}"
    ),
    NavItem(
        label = "DeliveryInfoUI",
        icon = Icons.Filled.DeliveryDining,
        route = Screens.DeliveryInfoUI.name + "/{id}",
    )
)


