package com.xpridex.paymentapp.domain

import com.xpridex.paymentapp.data.PaymentsRepository
import com.xpridex.paymentapp.domain.mapper.PaymentMethodsMapper
import com.xpridex.paymentapp.domain.model.PaymentMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCreditCardPaymentMethodsUseCase @Inject constructor(
    private val repository: PaymentsRepository,
    private val mapper: PaymentMethodsMapper
) {

    operator fun invoke(): Flow<List<PaymentMethod>> = flow {
        repository.getPaymentMethods().collect { paymentMethods ->
            val creditMethods = paymentMethods.filter { it.paymentTypeId == CREDIT_CARD }
            emit(with(mapper) {
                creditMethods.toDomain()
            })
        }
    }

    companion object {
        const val CREDIT_CARD = "credit_card"
    }
}