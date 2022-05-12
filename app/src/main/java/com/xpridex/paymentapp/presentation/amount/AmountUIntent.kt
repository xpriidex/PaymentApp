package com.xpridex.paymentapp.presentation.amount

import com.xpridex.paymentapp.core.mvi.events.MviUserIntent

 sealed class AmountUIntent : MviUserIntent {
    data class ContinueUIntent(val amount: String) : AmountUIntent()
}
