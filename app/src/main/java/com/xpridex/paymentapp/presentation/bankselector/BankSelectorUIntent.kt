package com.xpridex.paymentapp.presentation.bankselector

import com.xpridex.paymentapp.core.mvi.events.MviUserIntent
import com.xpridex.paymentapp.presentation.bankselector.model.Bank

sealed class BankSelectorUIntent : MviUserIntent {
    object InitialUIntent : BankSelectorUIntent()
     data class SelectBank(val bank: Bank) : BankSelectorUIntent()
}
