package com.xpridex.paymentapp.domain

import com.xpridex.paymentapp.data.PaymentsRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SavePaymentMethodUseCase @Inject constructor(
    private val repository: PaymentsRepository
) {

    operator fun invoke(paymentMethod:String) = runBlocking {
        repository.savePaymentMethod(paymentMethod)
    }
}