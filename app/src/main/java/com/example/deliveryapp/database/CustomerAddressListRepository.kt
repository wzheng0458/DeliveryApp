package com.example.deliveryapp.database
//
//class CustomerAddressListRepository(private val customerAddressDao: CustomerAddressDao) {
//
//
//        suspend fun addCustomer(item: List<Customer>) {
//                customerAddressDao.insertCustomer(item)
//        }
//
//        suspend fun addAddress(address: Address) {
//                customerAddressDao.insertAddress(address)
//        }
//
//        suspend fun editAddress(address: Address){
//                customerAddressDao.editAddress(address)
//        }
//
//        suspend fun deleteAddress(customer: Customer){
//                customerAddressDao.deleteAddress(customer)
//        }
//
//        fun getCustomerAddress(customerId: String): List<CustomerAddressList> {
//                return customerAddressDao.getCustomerAddressList(customerId)
//        }
//}