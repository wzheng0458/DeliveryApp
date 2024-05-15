package com.example.deliveryapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update

@Dao
interface CustomerAddressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomer(item: List<Customer>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddress(address: Address)

    @Delete
    suspend fun deleteAddress(customerId: String)

    @Update
    suspend fun editAddress(address: Address)

    @Transaction
    @Query("SELECT * FROM Customer WHERE customerId = :customerId")
    fun getCustomerAddressList(customerId: String): LiveData<List<CustomerAddressList>>



}