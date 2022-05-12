package com.xpridex.paymentapp.data.cache

import com.xpridex.paymentapp.data.cache.datastore.PaymentsDataStore
import com.xpridex.paymentapp.data.source.PaymentsCache
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.take
import javax.inject.Inject

internal class PaymentsCacheImpl @Inject constructor(
    private val dataStore: PaymentsDataStore
) : PaymentsCache {

    override suspend fun saveAmount(amount: String) {
        dataStore.saveAmount(amount)
    }

    override fun getAmount(): Flow<String> = dataStore.getAmount().take(1)
}
