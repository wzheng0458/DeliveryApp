package com.example.deliveryapp


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
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
        route = Screens.Home.name
    ),
    NavItem(
        label = "DeliveryInfoUI",
        icon = Icons.Filled.LocationOn,
        route = Screens.DeliveryInfoUI.name,
    ),
//    NavItem(
//        label = "MapScreenComponent",
//        icon = Icons.Default.Home,
//        route = Screens.MapScreenComponent.name
//    ),

)


