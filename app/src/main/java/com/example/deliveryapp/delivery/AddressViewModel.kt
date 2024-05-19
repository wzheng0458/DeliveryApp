package com.example.deliveryapp.delivery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.deliverydatabase.Address
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class AddressViewModel : ViewModel() {

    val addressDao = AppApplication.addressDatabase.getAddressDao()

    fun getListAddressById(customerId: String): LiveData<List<Address>> {
        return addressDao.getAllAddressById(customerId)
    }
    fun getAddress(id: Int): LiveData<Address>{
        return addressDao.getAddress(id)
    }

    fun addAddress(address: String,
                   meetOption: String,
                   unit: String,
                   state: String,
                   desc: String,
                   customerId: String){
        viewModelScope.launch(Dispatchers.IO) {
            addressDao.addAddress(Address(address = address, meetOption = meetOption, unit =unit,state = state, desc = desc, ownerId = customerId, createdAt = Date.from(Instant.now())))
        }
    }

    fun updateAddress(id: Int,
                      address: String,
                      meetOption: String,
                      unit: String,
                      state: String,
                      desc: String,
                      ownerId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            addressDao.updateAddress(Address(id = id ,address = address, meetOption = meetOption, unit =unit,state = state, desc = desc, ownerId = ownerId, Date.from(Instant.now())))
        }
    }

    fun deleteAddress(id : Int){
        viewModelScope.launch(Dispatchers.IO) {
            addressDao.deleteAddress(id)
        }
    }


}