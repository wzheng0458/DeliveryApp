package com.example.deliveryapp.orderdatabase


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//class OrderViewModelFactory(private val orderRepository: OrderRepository) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(OrderViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return OrderViewModel(orderRepository) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}