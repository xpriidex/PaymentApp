package com.xpridex.paymentapp.presentation.home.model

data class Payment(
    val id: Long,
    val amount: String,
    val paymentMethod: String,
    val bankId: String,
    val bankName: String,
    val recommendedMessage: String
)