package com.example.deliveryapp.database

//import androidx.room.Dao
//import androidx.room.Insert
//import androidx.room.OnConflictStrategy
//import androidx.room.Query
//import androidx.room.Transaction
//
//@Dao
//interface CustomerAddressDao {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertCustomer(item: List<Customer>)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAddress(item: Address)
//
//    @Transaction
//    @Query("SELECT * FROM Customer WHERE customerId = :customerId")
//    fun getCustomerAddressList(customerId: String): List<CustomerAddressList>
//
//
//
//}