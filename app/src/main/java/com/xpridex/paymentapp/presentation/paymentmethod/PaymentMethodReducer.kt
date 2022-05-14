package com.xpridex.paymentapp.presentation.paymentmethod

import com.xpridex.paymentapp.core.mvi.MviReducer
import com.xpridex.paymentapp.presentation.paymentmethod.PaymentMethodResult.GetPaymentMethodsResult
import com.xpridex.paymentapp.presentation.paymentmethod.PaymentMethodResult.SavePaymentMethodResult
import javax.inject.Inject

class PaymentMethodReducer @Inject constructor() :
    MviReducer<PaymentMethodUiState, PaymentMethodResult> {

    override fun PaymentMethodUiState.reduceWith(result: PaymentMethodResult): PaymentMethodUiState {
        val previousState = this
        return when (result) {
            GetPaymentMethodsResult.InProgress -> previousState.copy(
                isLoading = true
            )
            is GetPaymentMethodsResult.Success -> previousState.copy(
                isLoading = false,
                paymentMethods = result.paymentMethods
            )
            GetPaymentMethodsResult.Empty -> previousState.copy(isEmpty = true)
            GetPaymentMethodsResult.Error -> previousState.copy(isEmpty = true)
            is SavePaymentMethodResult.NavigateToBankSelector -> previousState
        }
    }
}
