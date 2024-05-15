package com.example.deliveryapp.database

import androidx.lifecycle.LiveData

class CustomerAddressListRepository(private val customerAddressDao: CustomerAddressDao) {
    var readAllData: LiveData<List<CustomerAddressList>>? = null

    suspend fun addCustomer(item: List<Customer>) {
        customerAddressDao.insertCustomer(item)
    }

    suspend fun addAddress(address: Address) {
        customerAddressDao.insertAddress(address)
    }

    suspend fun editAddress(address: Address){
        customerAddressDao.editAddress(address)
    }

    suspend fun deleteAddress(customerId: String){
        customerAddressDao.deleteAddress(customerId)
    }

    fun getCustomerAddress(customerId: String): LiveData<List<CustomerAddressList>> {
        return customerAddressDao.getCustomerAddressList(customerId)
    }
}