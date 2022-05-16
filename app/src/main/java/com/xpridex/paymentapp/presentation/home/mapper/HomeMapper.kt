package com.xpridex.paymentapp.presentation.home.mapper

import com.xpridex.paymentapp.data.cache.database.model.PaymentEntity
import com.xpridex.paymentapp.presentation.home.model.Payment
import javax.inject.Inject

class HomeMapper @Inject constructor() {

    fun List<PaymentEntity>.toPayment() = map { paymentEntity ->
        paymentEntity.toPayment()
    }

    private fun PaymentEntity.toPayment() = Payment(
        id = id,
        amount = amount,
        paymentMethod = paymentMethod,
        bankId = bankId,
        bankName = bankName,
        recommendedMessage = recommendedMessage
    )
}
