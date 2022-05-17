package com.xpridex.paymentapp.presentation.bankselector

import com.xpridex.paymentapp.core.mvi.events.MviResult
import com.xpridex.paymentapp.presentation.bankselector.model.Bank

sealed class BankSelectorResult : MviResult {

    sealed class GetBanksResult : BankSelectorResult() {
        object InProgress : GetBanksResult()
        data class Success(val banks: List<Bank>) : GetBanksResult()
        object Error : GetBanksResult()
        object Empty : GetBanksResult()
    }

    sealed class SaveBankResult : BankSelectorResult() {
        object NavigateToInstallments : SaveBankResult()
    }
}
