package com.xpridex.paymentapp.data.cache

import com.xpridex.paymentapp.data.cache.database.dao.PaymentsDao
import com.xpridex.paymentapp.data.cache.datastore.PaymentsDataStore
import io.mockk.coEvery
import io.mockk.coJustRun
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

class PaymentsCacheImplTest {
    private val dataStore = mockk<PaymentsDataStore>()
    private val paymentsDao = mockk<PaymentsDao>()

    private val cacheImpl = PaymentsCacheImpl(dataStore, paymentsDao)

    @Test
    fun `when call saveAmount then clear and saveAmount`() = runBlocking {
        val amount = "amount"
        stubClearDataStore()
        stubSaveAmountDataStore(amount)

        cacheImpl.saveAmount(amount)

        coVerify { dataStore.clear() }
        coVerify { dataStore.saveAmount(amount) }
    }

    @Test
    fun `when getAmount, then return amount`() =
        runBlocking {
            val amount = "amount"
            stubGetAmount(amount)

            val flow = cacheImpl.getAmount()

            flow.collect { result ->
                assertEquals(amount, result)
            }
        }

    @Test
    fun `when call savePaymentMethod then call dataStore`() = runBlocking {
        val paymentMethod = "paymentMethod"
        stubSavePaymentMethod(paymentMethod)

        cacheImpl.savePaymentMethod(paymentMethod)

        coVerify { dataStore.savePaymentMethod(paymentMethod) }
    }

    @Test
    fun `when getPaymentMethod, then return PaymentMethod`() =
        runBlocking {
            val paymentMethod = "paymentMethod"
            stubGetPaymentMethod(paymentMethod)

            val flow = cacheImpl.getPaymentMethod()

            flow.collect { result ->
                assertEquals(paymentMethod, result)
            }
        }

    private fun stubClearDataStore() {
        coJustRun { dataStore.clear() }
    }

    private fun stubSaveAmountDataStore(amount: String) {
        coJustRun { dataStore.saveAmount(amount) }
    }

    private fun stubGetAmount(amount: String) {
        coEvery { dataStore.getAmount().take(1) } returns flow { emit(amount) }
    }

    private fun stubSavePaymentMethod(paymentMethod: String) {
        coJustRun { dataStore.savePaymentMethod(paymentMethod) }
    }

    private fun stubGetPaymentMethod(paymentMethod: String) {
        coEvery { dataStore.getPaymentMethod().take(1) } returns flow { emit(paymentMethod) }
    }

}