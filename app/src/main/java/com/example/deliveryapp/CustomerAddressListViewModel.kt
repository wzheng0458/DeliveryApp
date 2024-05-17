package com.example.deliveryapp

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.database.Address
import com.example.deliveryapp.database.Customer

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
//
//class CustomerAddressListViewModel(application: Application) : AndroidViewModel(application){
//
//
//    private val repository: CustomerAddressListRepository
//
//    // UI State using MutableState
//    private val _uiState = mutableStateOf(CustomerUiState())
//    val uiState: MutableState<CustomerUiState> = _uiState
//
//    init {
//        val customerAddressDao = CustomerAddressListDatabase.getInstance(application).customerAddressDao()
//        repository = CustomerAddressListRepository(customerAddressDao)
//    }
//
//
//    fun getCustomer(customerId: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            // Update UI state to show loading
//            _uiState.value = _uiState.value.copy(loading = true)
//
//            // Fetch data from repository
//            val customerAddresses = repository.getCustomerAddress(customerId)
//
//            // Update UI state with fetched data
//            _uiState.value = _uiState.value.copy(customer = customerAddresses, loading = false)
//        }
//    }
//    fun addCustomer(item: List<Customer>) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.addCustomer(item)
//        }
//    }
//
//
//    fun addAddress(address: Address) {
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.addAddress(address)
//        }
//    }
//
//    fun editAddress(address: Address){
//        viewModelScope.launch(Dispatchers.IO){
//            repository.editAddress(address)
//        }
//    }
//
//    fun deleteAddress(customer: Customer){
//        viewModelScope.launch(Dispatchers.IO){
//            repository.deleteAddress(customer)
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
//
//
//
//
//
//
//data class CustomerUiState(
//    val loading: Boolean = false,
//    val customer: List<CustomerAddressList> = emptyList()
//)




