package com.xpridex.paymentapp.data.remote

import com.xpridex.paymentapp.data.remote.api.PaymentsApi
import com.xpridex.paymentapp.data.remote.model.PaymentMethodApiModel
import com.xpridex.paymentapp.data.source.PaymentsRemote
import javax.inject.Inject

class PaymentsRemoteImpl @Inject constructor(
    private val api: PaymentsApi
) : PaymentsRemote {

    override suspend fun getPaymentMethods(): List<PaymentMethodApiModel> = api.getPaymentMethods()
}
