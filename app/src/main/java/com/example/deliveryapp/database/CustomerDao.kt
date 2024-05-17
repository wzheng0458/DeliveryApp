package com.example.deliveryapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CustomerDao {
    @Insert
    fun insertCustomer(customer: Customer)

//    @Insert
//    suspend fun insertAddress(address: Address)


    @Query("SELECT * FROM Customer WHERE customerId = :customerId")
    fun getCustomerById(customerId: String): LiveData<Customer>


}