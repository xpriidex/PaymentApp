package com.xpridex.paymentapp.presentation.amount

import com.xpridex.paymentapp.core.mvi.MviReducer
import com.xpridex.paymentapp.presentation.amount.AmountResult.SaveAmountResult.Empty
import com.xpridex.paymentapp.presentation.amount.AmountResult.SaveAmountResult.NavigateToPaymentMethods
import javax.inject.Inject

class AmountReducer @Inject constructor() :
    MviReducer<AmountUiState, AmountResult> {

    override fun AmountUiState.reduceWith(result: AmountResult): AmountUiState {
        val previousState = this
        return when (result) {
            NavigateToPaymentMethods -> previousState
            Empty -> previousState.copy(isEmpty = true)
        }
    }
}
