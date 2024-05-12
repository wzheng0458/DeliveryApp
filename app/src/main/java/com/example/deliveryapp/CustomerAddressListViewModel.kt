package com.example.deliveryapp

//import android.app.Application
//import androidx.lifecycle.AndroidViewModel
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.MutableLiveData
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.viewModelScope
//import com.example.deliveryapp.database.Address
//import com.example.deliveryapp.database.Customer
//import com.example.deliveryapp.database.CustomerAddressList
//import com.example.deliveryapp.database.CustomerAddressListDatabase
//import com.example.deliveryapp.database.CustomerAddressListRepository
//import kotlinx.coroutines.Dispatchers
//import kotlinx.coroutines.launch
//
//
//class CustomerAddressListViewModel(application: Application) : AndroidViewModel(application){
//    private val _readAllData = MutableLiveData<List<CustomerAddressList>>()
//    var readAllData: LiveData<List<CustomerAddressList>> = _readAllData
//    private val repository: CustomerAddressListRepository
//
//    init {
//        val customerAddressDao = CustomerAddressListDatabase.getInstance(application).customerAddressDao()
//        repository = CustomerAddressListRepository(customerAddressDao)
//    }
//
//    fun getCustomer(customerId: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            _readAllData.postValue(repository.getCustomerAddress(customerId))
//        }
//    }
//
//    fun addCustomer(item: List<Customer>) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.addCustomer(item)
//        }
//    }
//
//    fun addAddress(item: Address) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.addAddress(item)
//        }
//    }
//}
//
//@Suppress("UNCHECKED_CAST")
//class CustomerAddressViewModelFactory(
//    private val application: Application
//): ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(CustomerAddressListViewModel::class.java)) {
//            return CustomerAddressListViewModel(application) as T
//        }
//        throw IllegalStateException("Unknown ViewModel class")
//    }
//}


