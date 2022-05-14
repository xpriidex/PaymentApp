package com.xpridex.paymentapp.domain.mapper

import com.xpridex.paymentapp.data.remote.model.PaymentMethodApiModel
import com.xpridex.paymentapp.domain.model.PaymentMethod
import javax.inject.Inject

class PaymentMethodsMapper @Inject constructor() {

    fun List<PaymentMethodApiModel>.toDomain(): List<PaymentMethod> =
        map { it.toDomain() }

    private fun PaymentMethodApiModel.toDomain() = PaymentMethod(
        id = id.orEmpty(),
        name = name.orEmpty(),
        urlImage = urlImage.orEmpty()
    )
}
