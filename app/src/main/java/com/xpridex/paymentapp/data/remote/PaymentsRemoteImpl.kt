package com.xpridex.paymentapp.data.remote

import com.xpridex.paymentapp.data.remote.api.PaymentsApi
import com.xpridex.paymentapp.data.remote.model.BankApiModel
import com.xpridex.paymentapp.data.remote.model.InstallmentsApiModel
import com.xpridex.paymentapp.data.remote.model.PayerCostApiModel
import com.xpridex.paymentapp.data.remote.model.PaymentMethodApiModel
import com.xpridex.paymentapp.data.source.PaymentsRemote
import javax.inject.Inject

class PaymentsRemoteImpl @Inject constructor(
    private val api: PaymentsApi
) : PaymentsRemote {

    override suspend fun getPaymentMethods(): List<PaymentMethodApiModel> = api.getPaymentMethods()

    override suspend fun getBanks(paymentMethod: String): List<BankApiModel> =
        api.getBanks(paymentMethod = paymentMethod)

    override suspend fun getInstallments(paymentMethod: String, bank: String): List<InstallmentsApiModel> =
        api.getInstallments(paymentMethod = paymentMethod, bankId = bank)
}
