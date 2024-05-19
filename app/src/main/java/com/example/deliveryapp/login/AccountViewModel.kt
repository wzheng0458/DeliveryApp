package com.example.deliveryapp.login

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deliveryapp.logindatabase.Account
import com.example.deliveryapp.logindatabase.AccountDao
import com.example.deliveryapp.logindatabase.AccountEvent
import com.example.deliveryapp.logindatabase.AccountState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AccountViewModel(
    private val dao: AccountDao
):ViewModel() {

    private val isSortedById = MutableStateFlow(true)
    private val _state = MutableStateFlow(AccountState())

    private val account: StateFlow<List<Account>> =
        isSortedById.flatMapLatest { sort ->
            if (sort) {
                dao.getAccountOrderedByUsernameAdded()
            } else {
                dao.getIdOrderedByUsernameAdded()
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


    val state = combine(
        _state,
        isSortedById,
        account
    ) { state, isSortedById, accountList ->
        state.copy(
            account = accountList
        )
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        AccountState()
    )


    fun onEvent(event: AccountEvent) {
        when (event) {
            is AccountEvent.DeleteAccount -> {
                viewModelScope.launch {
                    dao.deleteAccount(event.account)
                }
            }

            is AccountEvent.SaveAccount -> {
                val account = Account(
                    username = _state.value.username.value,
                    password = _state.value.password.value,
                    passwordConfirmation = _state.value.passwordConfirmation.value,
                    email = _state.value.email.value,
                    phoneNumber = _state.value.phoneNumber.value,
                    id = _state.value.id.value
                )

                viewModelScope.launch {
                    dao.addAccount(account)
                }


                _state.update {
                    it.copy(
                        username = mutableStateOf(""),
                        password = mutableStateOf(""),
                        passwordConfirmation = mutableStateOf(""),
                        email = mutableStateOf(""),
                        phoneNumber = mutableStateOf(""),
                        id = mutableStateOf(""),

                        )
                }
            }

            AccountEvent.SortAccount -> {
                isSortedById.value = !isSortedById.value
            }
        }
    }
}