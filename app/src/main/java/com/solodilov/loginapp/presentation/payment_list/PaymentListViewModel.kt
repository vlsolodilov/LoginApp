package com.solodilov.loginapp.presentation.payment_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solodilov.loginapp.domain.entity.Payment
import com.solodilov.loginapp.domain.usecase.GetPaymentListUseCase
import com.solodilov.loginapp.presentation.common.Result
import com.solodilov.loginapp.presentation.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PaymentListViewModel @Inject constructor(
    private val getPaymentListUseCase: GetPaymentListUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Payment>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<Payment>>> = _uiState

    init {
        getData()
    }

    fun getData() {
        viewModelScope.launch {
            flow {
                emit(Result.Loading)
                try {
                    emit(Result.Success(getPaymentListUseCase()))
                } catch (e: Exception) {
                    emit(Result.Error(e))
                }
            }.collect { result ->
                _uiState.value = when (result) {
                    is Result.Loading -> UiState.Loading
                    is Result.Success -> UiState.Success(result.data)
                    is Result.Error -> UiState.Error(result.exception)
                }
            }
        }
    }

}