package com.xpridex.paymentapp.data.source

import kotlinx.coroutines.flow.Flow

interface PaymentsCache {
    suspend fun saveAmount(amount: String)
    fun getAmount(): Flow<String>
}
