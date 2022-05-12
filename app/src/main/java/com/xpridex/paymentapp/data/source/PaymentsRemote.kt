package com.xpridex.paymentapp.data.source

import com.xpridex.paymentapp.data.remote.model.PaymentMethodApiModel

interface PaymentsRemote {
    suspend fun getPaymentMethods(): List<PaymentMethodApiModel>
}