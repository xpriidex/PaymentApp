package com.xpridex.paymentapp.data.source

import com.xpridex.paymentapp.data.cache.database.model.PaymentEntity
import com.xpridex.paymentapp.data.remote.model.BankApiModel
import com.xpridex.paymentapp.data.remote.model.InstallmentsApiModel
import com.xpridex.paymentapp.data.remote.model.PaymentMethodApiModel

interface PaymentsRemote {
    suspend fun getPaymentMethods(): List<PaymentMethodApiModel>
    suspend fun getBanks(paymentMethod: String): List<BankApiModel>
    suspend fun getInstallments(paymentMethod: String, bank: String): List<InstallmentsApiModel>
}