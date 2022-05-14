package com.xpridex.paymentapp.presentation.bankselector

import com.xpridex.paymentapp.core.mvi.MviReducer
import com.xpridex.paymentapp.presentation.bankselector.BankSelectorResult.GetBanksResult
import com.xpridex.paymentapp.presentation.bankselector.BankSelectorResult.SaveBankResult.NavigateToInstallments
import javax.inject.Inject

class BankSelectorReducer @Inject constructor() :
    MviReducer<BankSelectorUiState, BankSelectorResult> {

    override fun BankSelectorUiState.reduceWith(result: BankSelectorResult): BankSelectorUiState {
        val previousState = this
        return when (result) {
            GetBanksResult.InProgress -> previousState.copy(
                isLoading = true
            )
            is GetBanksResult.Success -> previousState.copy(
                isLoading = false,
                banks = result.banks
            )
            GetBanksResult.Empty -> previousState.copy(isEmpty = true)
            GetBanksResult.Error -> previousState.copy(isEmpty = true)
            is NavigateToInstallments -> previousState
        }
    }
}
