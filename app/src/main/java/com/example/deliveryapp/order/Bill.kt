package com.example.deliveryapp.order

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.deliveryapp.R
import com.example.deliveryapp.Screens
import com.example.deliveryapp.delivery.ConfirmDeliveryOrderViewModel
import com.example.deliveryapp.orderdatabase.CartViewModel
//import com.example.deliveryapp.orderdatabase.OrderViewModel
//import com.example.deliveryapp.orderdatabase.Order
import kotlinx.coroutines.launch
import java.text.DecimalFormat

@Composable
fun Bill(
    cartViewModel: CartViewModel,
    navController: NavHostController,
//    orderViewModel: OrderViewModel,
    //customerId: String, // Customer ID
    //tableNumber: Int, // Table number
    id: String,
    confirmDeliveryOrderViewModel: ConfirmDeliveryOrderViewModel,
    isDeliveryOrder: Int?

) {
    val products by cartViewModel.cartProducts.collectAsState()
    val totalPrice = products.sumOf { it.pQuantity * it.pPrice }
    val serviceTax = totalPrice * 0.06
    val finalPrice = totalPrice + serviceTax

    val decimalFormat = DecimalFormat("#.##")
    val scope = rememberCoroutineScope()

    val customerId = id
    val tableNumber = "T1"

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = null,
            modifier = Modifier
                .height(64.dp)
                .aspectRatio(1f)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Bill",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Customer ID: $customerId", style = MaterialTheme.typography.bodyMedium)
        if (isDeliveryOrder == 0){
            Text(text = "Table Number: $tableNumber", style = MaterialTheme.typography.bodyMedium)

        }
        Spacer(modifier = Modifier.height(8.dp))

        products.forEach { product ->
            Text(
                text = "${product.pName}: ${product.pQuantity} x ${decimalFormat.format(product.pPrice)}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        if(isDeliveryOrder == 1){
            Text(text = "Total Price: RM ${decimalFormat.format(totalPrice)}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Service Tax (6%): RM ${decimalFormat.format(serviceTax)}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Delivery Fee: RM 3.00", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Final Price: RM ${decimalFormat.format(finalPrice + 3)}", style = MaterialTheme.typography.bodyMedium)
        }else{
            Text(text = "Total Price: RM ${decimalFormat.format(totalPrice)}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Service Tax (6%): RM ${decimalFormat.format(serviceTax)}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Final Price: RM ${decimalFormat.format(finalPrice)}", style = MaterialTheme.typography.bodyMedium)
        }


        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
//            scope.launch {
//                // Insert each product as a separate order
//                products.forEach { product ->
//                    val order = Order(
//                        pId = product.pId,
//                        oPrice = product.pPrice,
//                        oQuantity = product.pQuantity,
//                        oTable = tableNumber,
//                        customerId = customerId
//                    )
//                    orderViewModel.insertOrder(order)
//                }
                // Clear the cart
                cartViewModel.clearCart()
                // Navigate back to menu

                navController.navigate(Screens.OrderMenu.name + "/${id} " + "/${isDeliveryOrder}")


//            }
        }) {
            Text(text = "Done")
        }
    }
}