package com.xpridex.paymentapp.presentation.paymentmethod

import com.xpridex.paymentapp.core.mvi.events.MviResult
import com.xpridex.paymentapp.data.remote.model.PaymentMethodApiModel
import com.xpridex.paymentapp.domain.model.PaymentMethod
import com.xpridex.paymentapp.domain.model.PaymentMethods

sealed class PaymentMethodResult : MviResult {

    sealed class GetPaymentMethodsResult : PaymentMethodResult() {
        object InProgress : GetPaymentMethodsResult()
        data class Success(val paymentMethods: List<PaymentMethod>) : GetPaymentMethodsResult()
        object Error : GetPaymentMethodsResult()
        object Empty : GetPaymentMethodsResult()
    }
}
