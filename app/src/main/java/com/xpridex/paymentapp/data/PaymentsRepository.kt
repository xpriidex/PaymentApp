package com.xpridex.paymentapp.data

import com.xpridex.paymentapp.data.cache.database.model.PaymentEntity
import com.xpridex.paymentapp.data.remote.model.BankApiModel
import com.xpridex.paymentapp.data.remote.model.InstallmentsApiModel
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
        val paymentMethods = remote.getPaymentMethods()
        emit(paymentMethods)
    }

    fun saveAmount(amount: String) = runBlocking {
        cache.saveAmount(amount = amount)
    }

    fun savePaymentMethod(paymentMethod: String) = runBlocking {
        cache.savePaymentMethod(amount = paymentMethod)
    }

    fun getBanks(): Flow<List<BankApiModel>> = flow {
        cache.getPaymentMethod().collect { paymentMethod ->
            val banks = remote.getBanks(paymentMethod)
            emit(banks)
        }
    }

    fun saveBank(id: String, name: String) = runBlocking {
        cache.saveBank(id = id, name = name)
    }


    fun getInstallments(): Flow<List<InstallmentsApiModel>> = flow {
        cache.getPaymentMethod().collect { paymentMethod ->
            cache.getBankId().collect { id ->
                val banks = remote.getInstallments(paymentMethod = paymentMethod, bank = id)
                emit(banks)
            }
        }
    }

    fun saveRecommendedMessage(recommendedMessage: String) = runBlocking {
        cache.getAmount().collect { amount ->
            cache.getPaymentMethod().collect { paymentMethod ->
                cache.getBankInfo().collect { bank ->

                    val payment = PaymentEntity(
                        amount = amount,
                        paymentMethod = paymentMethod,
                        bankId = bank.id,
                        bankName = bank.name,
                        recommendedMessage = recommendedMessage
                    )
                    cache.savePayment(payment)
                }
            }
        }
    }

    fun getPayments(): Flow<List<PaymentEntity>> = flow {
        val payments = cache.getPayments()
        emit(payments)
    }
}