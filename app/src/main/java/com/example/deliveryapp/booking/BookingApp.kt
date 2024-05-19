package com.example.deliveryapp.booking

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.deliveryapp.Screens
import com.example.deliveryapp.booking.BookingViewModel
import com.example.deliveryapp.booking.TimeViewModel
import com.example.deliveryapp.booking.ViewModel


@Composable
fun BookingApp(
    navController: NavHostController,
    id: String?
) {
    val TabItems = listOf(
        TabItem(
            title = "Booking",
            unselectedIcon = Icons.Outlined.Search,
            selectedIcon = Icons.Outlined.Search
        ),
        TabItem(
            title = "History",
            unselectedIcon = Icons.Outlined.Info,
            selectedIcon = Icons.Outlined.Info
        ),
    )

    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
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
            0 ->{
                id?.let {
                    BookingScreen(
                        modifier = Modifier,
                        onSelected = {
                            navController.navigate(Screens.BookingScreen.name + "/${id}")
                        },
                        ViewModel = ViewModel(),
                        BookingViewModel(),
                        TimeViewModel(),
                        id,
                        navController
                    )
                }
            }




//                    composable( route = com.example.booking.BookingScreen.Info.name,
//                    )
//                    {
//                        InfoScreen(
//                            modifier = Modifier,
//                            onNextButtonClicked = { navController.navigate(com.example.booking.BookingScreen.Booking.name) },
//                            ViewModel = ViewModel(),
//
//                            )
//                    }


            1 -> RecordScreen(BookingViewModel = BookingViewModel(), id?: "")
        }
    }
}