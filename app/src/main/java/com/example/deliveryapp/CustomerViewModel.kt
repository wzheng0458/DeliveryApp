package com.example.deliveryapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.database.Customer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CustomerViewModel: ViewModel() {
    val customerDao = AppApplication.customerDatabase.getCustomerDao()

    fun getCustomerById(customerId: String): LiveData<Customer> {
        return customerDao.getCustomerById(customerId)
    }


    fun addCustomer(customerId: String, name: String, email: String, phoneNum: String){
        viewModelScope.launch(Dispatchers.IO) {
            customerDao.insertCustomer(
                Customer(customerId = customerId, name = name, email = email, phoneNum = phoneNum)
            )
        }
    }
//    fun updateAddress(id: Int, address: String, customerId: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            customerDao.updateCustomer(
//                Address(id = id, address = address, customerId = customerId, createdAt = Date.from(
//                    Instant.now()))
//            )
//        }
//    }

//    fun deleteCustomer(id : Int){
//        viewModelScope.launch(Dispatchers.IO) {
//            customerDao.deleteCustomer(id)
//        }
//    }
}