package com.example.loginapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.deliveryapp.R
import com.example.deliveryapp.Screens


@Composable
fun StaffMainScreen(navController: NavController, staffId: String?){

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(50.dp)
            .fillMaxSize()
    ){
        Text(
            text= "Welcome!",
            fontSize = 50.sp,
            modifier = Modifier.padding(20.dp),
            textAlign = TextAlign.Center
        )
        Button(onClick = { navController.navigate(Screens.ViewAccountScreen.name) }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.person),
                    contentDescription = "Account",
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = "View Account",
                    fontSize = 20.sp
                )
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = { navController.navigate(Screens.LoginMainScreen.name)}) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.booking),
                    contentDescription = "Booking",
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = "View Booking",
                    fontSize = 20.sp
                )
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = { /*TODO*/ }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.order),
                    contentDescription = "View Order",
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = "View Order",
                    fontSize = 20.sp
                )
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(onClick = { /*TODO*/ }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.delivery),
                    contentDescription = "View Delivery",
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = "View delivery",
                    fontSize = 20.sp
                )
            }
        }
        Spacer(modifier = Modifier.padding(10.dp))

        Button(onClick = { navController.navigate(Screens.LoginMainScreen.name) }) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .fillMaxWidth()
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.logout),
                    contentDescription = "Logout",
                    modifier = Modifier.size(48.dp)

                )
                Spacer(modifier = Modifier.padding(10.dp))
                Text(
                    text = "Log out",
                    fontSize = 20.sp
                )
            }
        }

    }
}