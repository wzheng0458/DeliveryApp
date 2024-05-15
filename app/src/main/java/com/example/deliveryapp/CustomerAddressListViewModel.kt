package com.example.deliveryapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.database.Address
import com.example.deliveryapp.database.Customer
import com.example.deliveryapp.database.CustomerAddressList
import com.example.deliveryapp.database.CustomerAddressListDatabase
import com.example.deliveryapp.database.CustomerAddressListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class CustomerAddressListViewModel(application: Application) : AndroidViewModel(application){
    private val _readAllData = MutableLiveData<LiveData<List<CustomerAddressList>>>()
    var readAllData: MutableLiveData<LiveData<List<CustomerAddressList>>> = _readAllData
    private val repository: CustomerAddressListRepository

    init {
        val customerAddressDao = CustomerAddressListDatabase.getInstance(application).customerAddressDao()
        repository = CustomerAddressListRepository(customerAddressDao)
    }

    fun getCustomer(customerId: String): LiveData<List<CustomerAddressList>> {
        return repository.getCustomerAddress(customerId)
    }

    fun addCustomer(item: List<Customer>) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCustomer(item)
        }
    }


    fun addAddress(address: Address) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAddress(address)
        }
    }

    fun editAddress(address: Address){
        viewModelScope.launch(Dispatchers.IO){
            repository.editAddress(address)
        }
    }

    fun deleteAddress(customerId: String){
        viewModelScope.launch(Dispatchers.IO){
            repository.deleteAddress(customerId)
        }
    }
}

@Suppress("UNCHECKED_CAST")
class CustomerAddressViewModelFactory(
    private val application: Application
): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CustomerAddressListViewModel::class.java)) {
            return CustomerAddressListViewModel(application) as T
        }
        throw IllegalStateException("Unknown ViewModel class")
    }
}


