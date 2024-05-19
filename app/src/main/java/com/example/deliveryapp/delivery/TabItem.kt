package com.example.deliveryapp.delivery

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Checklist
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.outlined.Checklist
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.ui.graphics.vector.ImageVector

data class TabItem(
    val title: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)


val TabItems = listOf(
    TabItem(
        title = "Your Delivery",
        unselectedIcon = Icons.Outlined.LocationOn,
        selectedIcon = Icons.Filled.LocationOn
    ),
    TabItem(
        title = "Delivery Record",
        unselectedIcon = Icons.Outlined.Checklist,
        selectedIcon = Icons.Filled.Checklist
    ),
)