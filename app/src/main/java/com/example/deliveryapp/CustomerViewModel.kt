package com.example.deliveryapp

//class CustomerViewModel: ViewModel() {
//    val customerDao = AppApplication.customerDatabase.getCustomerDao()
//
//    fun getCustomerById(customerId: String): LiveData<Customer> {
//        return customerDao.getCustomerWithAddressesById(customerId)
//    }
//
//
//    fun addCustomer(customerId: String, name: String, email: String, phoneNum: String){
//        viewModelScope.launch(Dispatchers.IO) {
//            customerDao.insertCustomer(
//                Customer(customerId = customerId, name = name, email = email, phoneNum = phoneNum)
//            )
//        }
//    }
//    fun updateAddress(id: Int, address: String, customerId: String) {
//        viewModelScope.launch(Dispatchers.IO) {
//            customerDao.updateC(
//                Address(id = id, address = address, customerId = customerId, createdAt = Date.from(
//                    Instant.now()))
//            )
//        }
//    }

//    fun deleteAddress(id : Int){
//        viewModelScope.launch(Dispatchers.IO) {
//            customerDao.deleteCustomer(id)
//        }
//    }
//}