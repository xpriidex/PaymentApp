package com.xpridex.paymentapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xpridex.paymentapp.core.mvi.MviPresentation
import com.xpridex.paymentapp.presentation.paymentmethod.PaymentMethodAction
import com.xpridex.paymentapp.presentation.paymentmethod.PaymentMethodAction.GetPaymentMethodsAction
import com.xpridex.paymentapp.presentation.paymentmethod.PaymentMethodProcessor
import com.xpridex.paymentapp.presentation.paymentmethod.PaymentMethodReducer
import com.xpridex.paymentapp.presentation.paymentmethod.PaymentMethodResult
import com.xpridex.paymentapp.presentation.paymentmethod.PaymentMethodUIntent
import com.xpridex.paymentapp.presentation.paymentmethod.PaymentMethodUIntent.ContinueUIntent
import com.xpridex.paymentapp.presentation.paymentmethod.PaymentMethodUIntent.InitialUIntent
import com.xpridex.paymentapp.presentation.paymentmethod.PaymentMethodUiState
import com.xpridex.paymentapp.ui.navigation.PaymentsNavActions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan
import javax.inject.Inject

@HiltViewModel
class PaymentMethodViewModel @Inject constructor(
    private val reducer: PaymentMethodReducer,
    private val processor: PaymentMethodProcessor
) : ViewModel(), MviPresentation<PaymentMethodUIntent, PaymentMethodUiState> {

    var navActions: PaymentsNavActions? = null

    val defaultUiState: PaymentMethodUiState = PaymentMethodUiState(
        isLoading = false,
        amount = "",
        paymentMethods = emptyList(),
        isEmpty = false,
    )
    private val uiState = MutableStateFlow(defaultUiState)

    override fun processUserIntents(userIntent: PaymentMethodUIntent) {
        processor.actionProcessor(userIntent.toAction())
            .checkResultForNav()
            .scan(defaultUiState) { previousUiState, result ->
                with(reducer) { previousUiState reduceWith result }
            }
            .onEach { uiState ->
                this.uiState.value = uiState
            }
            .launchIn(viewModelScope)
    }

    private fun PaymentMethodUIntent.toAction(): PaymentMethodAction {
        return when (this) {
            is InitialUIntent -> GetPaymentMethodsAction
            is ContinueUIntent -> GetPaymentMethodsAction
        }
    }

    override fun uiStates(): StateFlow<PaymentMethodUiState> = uiState


    private fun Flow<PaymentMethodResult>.checkResultForNav(): Flow<PaymentMethodResult> =
        onEach { result ->
            when (result) {
                // NavigateToPaymentMethods -> navActions?.paymentMethods?.invoke()
                else -> return@onEach
            }
        }
}