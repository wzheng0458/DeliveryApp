package com.example.deliveryapp.delivery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.deliverydatabase.Address
import com.example.deliveryapp.deliverydatabase.ConfirmDeliveryOrder
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class ConfirmDeliveryOrderViewModel: ViewModel() {
    val confirmDeliveryOrderDao = AppApplication.confirmDeliveryOrderDatabase.getConfirmDeliveryOrderDao()

    fun getListOrderById(deliveryOwnerId: String): LiveData<List<ConfirmDeliveryOrder>> {
        return confirmDeliveryOrderDao.getAllOrderById(deliveryOwnerId)
    }
    fun insertOrder(address: Address?, selectedDate: String, selectedTime: String) {
        val order = ConfirmDeliveryOrder(
            deliveryAddress = address!!.address,
            deliveryMeetOption = address.meetOption,
            deliveryUnit = address.unit,
            deliveryState = address.state,
            deliveryDesc = address.desc,
            deliveryOwnerId = address.ownerId,
            deliveryStatus = false, // Initial status
            deliveryDate = selectedDate,
            deliveryTime = selectedTime,
            createdAt = Date.from(Instant.now())
        )
        viewModelScope.launch {
            confirmDeliveryOrderDao.insertOrder(order)
        }
    }

    fun deleteOrder(id: Int) {
        viewModelScope.launch {
            confirmDeliveryOrderDao.deleteOrder(id)
        }
    }
}