package com.xpridex.paymentapp.presentation.bankselector

import com.xpridex.paymentapp.core.mvi.events.MviAction


sealed class BankSelectorAction : MviAction {
    object GetBanksAction : BankSelectorAction()
    data class SaveBankAction(val bank: String) : BankSelectorAction()
}
