package com.xpridex.paymentapp.domain.model

data class PaymentMethod(
    val id: String,
    val name: String,
    val paymentTypeId: String,
    val urlImage: String
)