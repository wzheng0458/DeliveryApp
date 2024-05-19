package com.example.deliveryapp.orderdatabase


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.delivery.AppApplication
import com.example.deliveryapp.deliverydatabase.Address
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class OrderViewModel: ViewModel() {
    val orderDao = AppApplication.orderDatabase.getOrderDao()

    // Function to insert an order
    fun insertOrder(  pId: String,
                      oPrice: Double,
                      oQuantity: Int ,
                      customerId: String ) {
        viewModelScope.launch(Dispatchers.IO) {
            orderDao.insert(
                Order(pId= pId, oPrice = oPrice, oQuantity = oQuantity, customerId = customerId)
            )
        }
    }
}
