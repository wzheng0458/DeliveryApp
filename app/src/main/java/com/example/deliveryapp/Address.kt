package com.example.deliveryapp

data class Address(
    var id: Int,
    var address: String
)
fun getAddress() : List<Address>{
    return listOf<Address>(
        Address(1,"address1"),
        Address(2,"address2"),
        Address(3,"address3")
    )
}
