package com.xpridex.paymentapp.presentation.amount

import com.xpridex.paymentapp.data.PaymentsRepository
import com.xpridex.paymentapp.presentation.amount.AmountAction.SaveAmountAction
import com.xpridex.paymentapp.presentation.amount.AmountResult.SaveAmountResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class AmountProcessor @Inject constructor(
    private val repository: PaymentsRepository
) {
    fun actionProcessor(actions: AmountAction): Flow<AmountResult> =
        when (actions) {
            is SaveAmountAction -> saveAmountProcessor(actions.amount)
        }

    private fun saveAmountProcessor(amount: String): Flow<AmountResult> = flow {
        if (amount.isBlank()) {
            emit(SaveAmountResult.Empty)
        } else {
            runCatching {
                repository.saveAmount(amount)
            }.onSuccess {
                emit(SaveAmountResult.NavigateToPaymentMethods)
            }
        }
    }
}
