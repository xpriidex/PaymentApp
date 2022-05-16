package com.xpridex.paymentapp.presentation.bankselector

import com.xpridex.paymentapp.core.mvi.events.MviAction
import com.xpridex.paymentapp.presentation.bankselector.model.Bank


sealed class BankSelectorAction : MviAction {
    object GetBanksAction : BankSelectorAction()
    data class SaveBankAction(val bank: Bank) : BankSelectorAction()
}
