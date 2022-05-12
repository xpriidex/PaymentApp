package com.xpridex.paymentapp.presentation.amount

import com.xpridex.paymentapp.core.mvi.MviReducer
import com.xpridex.paymentapp.core.mvi.UnsupportedReduceException
import com.xpridex.paymentapp.presentation.amount.AmountUiState.DisplayAmountInputState
import com.xpridex.paymentapp.presentation.amount.AmountUiState.EmptyUiState
import javax.inject.Inject

class AmountReducer @Inject constructor() :
    MviReducer<AmountUiState, AmountResult> {

    override fun AmountUiState.reduceWith(result: AmountResult): AmountUiState {
        return when (val previousState = this) {
            is DisplayAmountInputState -> previousState reduceWith result
            is EmptyUiState -> previousState reduceWith result
        }
    }

    private infix fun DisplayAmountInputState.reduceWith(result: AmountResult): AmountUiState {
        return when (result) {
            AmountResult.SaveAmountResult.NavigateToPaymentMethods -> this
            AmountResult.SaveAmountResult.Empty -> EmptyUiState
        }
    }

    private infix fun EmptyUiState.reduceWith(result: AmountResult): AmountUiState {
        throw unsupportedReduceCase(this, result)
    }

    private fun unsupportedReduceCase(
        uiState: AmountUiState,
        result: AmountResult
    ): Throwable {
        return UnsupportedReduceException(uiState, result)
    }
}
