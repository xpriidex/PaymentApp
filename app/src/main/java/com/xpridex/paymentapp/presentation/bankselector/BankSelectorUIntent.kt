package com.xpridex.paymentapp.presentation.bankselector

import com.xpridex.paymentapp.core.mvi.events.MviUserIntent

sealed class BankSelectorUIntent : MviUserIntent {
    object InitialUIntent : BankSelectorUIntent()
     data class SelectBank(val bank: String) : BankSelectorUIntent()
}
