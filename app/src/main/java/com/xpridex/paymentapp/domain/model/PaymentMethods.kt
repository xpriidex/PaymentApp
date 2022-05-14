package com.xpridex.paymentapp.domain.model

import com.xpridex.paymentapp.data.remote.model.PaymentMethodApiModel

data class PaymentMethods(
    val amount: String,
    val paymentMethods: List<PaymentMethodApiModel>
)