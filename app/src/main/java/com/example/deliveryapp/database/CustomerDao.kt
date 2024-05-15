package com.example.deliveryapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction

//@Dao
//interface CustomerDao {
//    @Insert
//    fun insertCustomer(customer: Customer)
//
////    @Insert
////    suspend fun insertAddress(address: Address)
//
//    @Transaction
//    @Query("SELECT * FROM Customer")
//    fun getCustomersWithAddresses(): LiveData<List<Customer>>
//
//    @Transaction
//    @Query("SELECT * FROM Customer WHERE customerId = :customerId")
//    fun getCustomerWithAddressesById(customerId: String): LiveData<Customer>
//
//
//}