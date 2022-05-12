package com.xpridex.paymentapp.presentation.amount

import com.xpridex.paymentapp.core.mvi.events.MviUiState

 sealed class AmountUiState : MviUiState {
    object DisplayAmountInputState : AmountUiState()
    object EmptyUiState : AmountUiState()
}
