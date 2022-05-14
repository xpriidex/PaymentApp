package com.xpridex.paymentapp.data.remote.api

import com.xpridex.paymentapp.data.remote.model.BankApiModel
import com.xpridex.paymentapp.data.remote.model.Constants.API_KEY
import com.xpridex.paymentapp.data.remote.model.Constants.ISSUER_ID
import com.xpridex.paymentapp.data.remote.model.Constants.PAYMENT_METHOD_ID
import com.xpridex.paymentapp.data.remote.model.Constants.PUBLIC_KEY
import com.xpridex.paymentapp.data.remote.model.InstallmentsApiModel
import com.xpridex.paymentapp.data.remote.model.PaymentMethodApiModel
import retrofit2.http.GET
import retrofit2.http.Query

interface PaymentsApi {
    @GET("v1/payment_methods")
    suspend fun getPaymentMethods(@Query(PUBLIC_KEY) apiKey: String = API_KEY): List<PaymentMethodApiModel>

    @GET("v1/payment_methods/card_issuers")
    suspend fun getBanks(
        @Query(PUBLIC_KEY) apiKey: String = API_KEY,
        @Query(PAYMENT_METHOD_ID) paymentMethod: String,
    ): List<BankApiModel>


    @GET("v1/payment_methods/installments")
    suspend fun getInstallments(
        @Query(PUBLIC_KEY) apiKey: String = API_KEY,
        @Query(PAYMENT_METHOD_ID) paymentMethod: String,
        @Query(ISSUER_ID) bankId: String
    ): List<InstallmentsApiModel>
}