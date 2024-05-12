package com.example.deliveryapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AddressDao {
    @Query("SELECT * FROM Address Join Customer on Address.customerId == Customer.customerId where Address.customerId = :customerId ORDER BY createdAt DESC")
    fun getAllAddressById(customerId : String) : LiveData<List<Address>>

    @Insert
    fun addAddress(address : Address)
    @Update
    fun updateAddress(address: Address)

    @Query("Delete FROM Address where id = :id")
    fun deleteAddress(id : Int)


}