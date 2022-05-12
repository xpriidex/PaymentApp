package com.xpridex.paymentapp.data.remote.api

import com.xpridex.paymentapp.data.remote.model.PaymentMethodApiModel
import retrofit2.http.GET

interface PaymentsApi {
    @GET("v1/payment_methods?public_key=444a9ef5-8a6b-429f-abdf-587639155d88#json")
    suspend fun getPaymentMethods(): List<PaymentMethodApiModel>
}