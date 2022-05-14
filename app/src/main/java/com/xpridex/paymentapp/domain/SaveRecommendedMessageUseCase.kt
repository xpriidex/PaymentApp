package com.xpridex.paymentapp.domain

import com.xpridex.paymentapp.data.PaymentsRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class SaveRecommendedMessageUseCase @Inject constructor(
    private val repository: PaymentsRepository
) {

    operator fun invoke(recommendedMessage: String) = runBlocking {
        repository.saveRecommendedMessage(recommendedMessage)
    }
}