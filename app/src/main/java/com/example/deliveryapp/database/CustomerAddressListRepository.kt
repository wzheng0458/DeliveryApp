package com.example.deliveryapp.database

//import androidx.lifecycle.LiveData
//
//class CustomerAddressListRepository(private val customerAddressDao: CustomerAddressDao) {
//    var readAllData: LiveData<List<CustomerAddressList>>? = null
//
//    suspend fun addCustomer(item: List<Customer>) {
//        customerAddressDao.insertCustomer(item = item)
//    }
//
//    suspend fun addAddress(item: Address) {
//        customerAddressDao.insertAddress(item = item)
//    }
//
//    fun getCustomerAddress(customerId: String): List<CustomerAddressList> {
//        return customerAddressDao.getCustomerAddressList(customerId = customerId)
//    }
//}