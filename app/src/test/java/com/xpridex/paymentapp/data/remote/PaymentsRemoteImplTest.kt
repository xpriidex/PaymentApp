package com.xpridex.paymentapp.data.remote

import com.xpridex.paymentapp.data.remote.api.PaymentsApi
import com.xpridex.paymentapp.data.remote.model.PaymentMethodApiModel
import com.xpridex.paymentapp.factory.PaymentMethodFactory.makePaymentMethodApiModelList
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PaymentsRemoteImplTest {
    private val api = mockk<PaymentsApi>()

    private val remoteImpl = PaymentsRemoteImpl(api)

    @Test
    fun `when call getPaymentMethods then return PaymentMethodApiModelList`() = runBlocking {
        val paymentMethodApiModelList = makePaymentMethodApiModelList(2)
        stubGetPaymentMethods(paymentMethodApiModelList)

        val result = remoteImpl.getPaymentMethods()

        assertEquals(paymentMethodApiModelList, result)
        coVerify { api.getPaymentMethods() }
    }

    private fun stubGetPaymentMethods(response: List<PaymentMethodApiModel>) {
        coEvery { api.getPaymentMethods() } returns response
    }
}