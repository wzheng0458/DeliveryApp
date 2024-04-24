package com.example.deliveryapp


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddressViewModel : ViewModel(){

    private var _addressList = MutableLiveData<List<Address>>()
    val addressList : LiveData<List<Address>> = _addressList

    fun getAllAddress(){
        _addressList.value = AddressManager.getAllAddress().reversed()
    }

    fun addAddress(address: String){
        AddressManager.addAddress(address)
        getAllAddress()
    }

    fun editAddress(id: Int, newAddress: String) {
        AddressManager.editAddress(id, newAddress)
        getAllAddress()
    }


    fun deleteAddress(id : Int){
        AddressManager.deleteAddress(id)
        getAllAddress()
    }
}