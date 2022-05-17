package com.xpridex.paymentapp.domain

import com.xpridex.paymentapp.data.PaymentsRepository
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import javax.inject.Inject

@FlowPreview
class GetRecommendedMessagesUseCase @Inject constructor(
    private val repository: PaymentsRepository
) {

    operator fun invoke(): Flow<List<String>> = flow {
        var recommendedMessage = emptyList<String>()
        val installments  = repository.getInstallments().flatMapConcat{ it.asFlow() }.toList()
        installments.forEach{installment ->
             recommendedMessage = installment.payerCosts?.map { it.recommendedMessage.orEmpty() }.orEmpty()
        }

        emit(recommendedMessage)
    }
}