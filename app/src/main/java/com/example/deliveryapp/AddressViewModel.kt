package com.example.deliveryapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.database.Address
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class AddressViewModel : ViewModel() {

    val addressDao = AppApplication.addressDatabase.getAddressDao()

    fun getListAddressById(customerId: String): LiveData<List<Address>> {
        return addressDao.getAllAddressById(customerId)
    }


    fun addAddress(address : String, customerId: String){
        viewModelScope.launch(Dispatchers.IO) {
            addressDao.addAddress(Address(address = address, customerId = customerId, createdAt = Date.from(Instant.now())))
        }
    }
    fun updateAddress(id: Int, address: String, customerId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addressDao.updateAddress(Address(id = id, address = address, customerId = customerId, createdAt = Date.from(Instant.now())))
        }
    }

    fun deleteAddress(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            addressDao.deleteAddress(id)
        }
    }


}