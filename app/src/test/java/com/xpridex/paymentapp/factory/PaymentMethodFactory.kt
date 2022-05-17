package com.xpridex.paymentapp.factory

import com.xpridex.paymentapp.data.remote.model.PaymentMethodApiModel

object PaymentMethodFactory {

    fun makePaymentMethodApiModelList(count: Int): List<PaymentMethodApiModel> =
        (0..count).map {
            makePaymentMethodApiModel()
        }

    fun makePaymentMethodApiModel() = PaymentMethodApiModel(
        id = "id",
        name = "name",
        paymentTypeId = "paymentTypeId",
        urlImage = "urlImage",
    )
}
