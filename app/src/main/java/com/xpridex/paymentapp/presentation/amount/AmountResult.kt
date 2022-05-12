package com.xpridex.paymentapp.presentation.amount

import com.xpridex.paymentapp.core.mvi.events.MviResult

 sealed class AmountResult : MviResult {

    sealed class SaveAmountResult : AmountResult() {
        object NavigateToPaymentMethods : SaveAmountResult()
        object Empty : SaveAmountResult()
    }
}
