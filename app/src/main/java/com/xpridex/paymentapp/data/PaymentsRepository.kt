package com.xpridex.paymentapp.data

import com.xpridex.paymentapp.data.remote.model.PaymentMethodApiModel
import com.xpridex.paymentapp.data.source.PaymentsCache
import com.xpridex.paymentapp.data.source.PaymentsRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class PaymentsRepository @Inject constructor(
    private val remote: PaymentsRemote,
    private val cache: PaymentsCache,
) {

    fun getPaymentMethods(): Flow<List<PaymentMethodApiModel>> = flow {
        val debtsTypes = remote.getPaymentMethods()
        emit(debtsTypes)
    }

    fun saveAmount(amount: String) = runBlocking {
        cache.saveAmount(amount = amount)
    }
}