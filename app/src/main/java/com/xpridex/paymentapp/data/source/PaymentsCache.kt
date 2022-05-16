package com.xpridex.paymentapp.data.source

import com.xpridex.paymentapp.data.cache.database.model.PaymentEntity
import kotlinx.coroutines.flow.Flow

interface PaymentsCache {
    suspend fun saveAmount(amount: String)
    fun getAmount(): Flow<String>
    suspend fun savePaymentMethod(amount: String)
    fun getPaymentMethod(): Flow<String>
    suspend fun saveBank(bank: String)
    fun getBank(): Flow<String>
    suspend fun savePayment(payment: PaymentEntity)
    suspend fun getPayments(): List<PaymentEntity>
}
