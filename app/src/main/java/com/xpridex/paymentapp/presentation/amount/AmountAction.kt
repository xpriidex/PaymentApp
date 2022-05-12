package com.xpridex.paymentapp.presentation.amount

import com.xpridex.paymentapp.core.mvi.events.MviAction


sealed class AmountAction : MviAction {
    data class SaveAmountAction(val amount: String) : AmountAction()
}
