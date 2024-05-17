package com.example.deliveryapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface AddressDao {
    @Query("SELECT * FROM Address where ownerId = :ownerId  ORDER BY createdAt DESC")
    fun getAllAddressById(ownerId : String) : LiveData<List<Address>>

    @Query("Select * from address where id = :id")
    fun getAddress(id: Int): LiveData<Address>

    @Insert
    fun addAddress(address : Address)
    @Update
    fun updateAddress(address: Address)

    @Query("Delete FROM Address where id = :id")
    fun deleteAddress(id : Int)


}