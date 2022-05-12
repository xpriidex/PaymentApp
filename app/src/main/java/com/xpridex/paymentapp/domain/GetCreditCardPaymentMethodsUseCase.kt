package com.xpridex.paymentapp.domain

import com.xpridex.paymentapp.data.PaymentsRepository
import com.xpridex.paymentapp.data.remote.model.PaymentMethodApiModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCreditCardPaymentMethodsUseCase @Inject constructor(private val repository: PaymentsRepository) {

    operator fun invoke(): List<PaymentMethodApiModel> {
        var creditCardPaymentMethods = emptyList<PaymentMethodApiModel>()
        repository.getPaymentMethods().map { remotePaymentMethods ->
            creditCardPaymentMethods =
                remotePaymentMethods.filter { it.paymentTypeId == CREDIT_CARD }
        }

        return creditCardPaymentMethods
    }

    companion object {
        const val CREDIT_CARD = "credit_card"
    }
}