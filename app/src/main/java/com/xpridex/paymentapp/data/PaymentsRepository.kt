package com.xpridex.paymentapp.data

import com.xpridex.paymentapp.data.remote.model.PaymentMethodApiModel
import com.xpridex.paymentapp.data.source.PaymentsRemote
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PaymentsRepository @Inject constructor(
    private val remote: PaymentsRemote
) {

    fun getPaymentMethods(): Flow<List<PaymentMethodApiModel>> = flow {
        val debtsTypes = remote.getPaymentMethods()
        emit(debtsTypes)
    }
}