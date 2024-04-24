package com.example.deliveryapp



object AddressManager {
    private val addressList = mutableListOf<Address>()

    fun getAllAddress(): List<Address>{
        return addressList
    }

    fun addAddress(address: String){
        addressList.add(Address(System.currentTimeMillis().toInt(), address))
    }

    fun editAddress(id: Int, newAddress: String) {
        val address = addressList.find { it.id == id }
        address?.let {
            it.address = newAddress
        }
    }

    fun deleteAddress(id : Int){
        addressList.removeIf{
            it.id == id
        }
    }
}