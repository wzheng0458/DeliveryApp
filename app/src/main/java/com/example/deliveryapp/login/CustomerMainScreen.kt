package com.example.deliveryapp.login


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.deliveryapp.R
import com.example.deliveryapp.Screens
import com.example.deliveryapp.logindatabase.FoodPromoData
import com.example.deliveryapp.logindatabase.FoodPromoSource


@Composable
fun CustomerMainScreen(navController: NavController, custId: String?) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // This is your promotional content display
            FoodPromoDisplay()
        }

        // Row to contain the button at the top right
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                onClick = { navController.navigate(Screens.LoginMainScreen.name) }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier.padding(vertical = 10.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.logout),
                        contentDescription = "Logout",
                        modifier = Modifier.size(48.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "Log out",
                        fontSize = 20.sp
                    )
                }
            }
        }
    }
}

@Composable
fun FoodPromoDisplay() {
    val foodPromoList = FoodPromoSource().loadFoodPromoData()
    FoodPromoList(foodPromoList)
}

@Composable
fun FoodPromoList(foodPromoList: List<FoodPromoData>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(foodPromoList.size) { index ->
            val foodPromo = foodPromoList[index]
            FoodPromoCard(
                foodPromo = foodPromo,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Composable
fun FoodPromoCard(foodPromo: FoodPromoData, modifier: Modifier = Modifier) {

    Card(modifier = modifier
        .size(650.dp)
        .fillMaxWidth()) {
        Column {
            Image(
                painter = painterResource(id = foodPromo.imageResourceId),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(600.dp),
                contentDescription = null, // Add content description if needed
                contentScale = ContentScale.Crop
            )
            Button(onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(Color(0xFFFFA500)),
                modifier = Modifier.align(Alignment.CenterHorizontally)){
                Text(text = "Order Now")
            }
        }
    }
}

