package com.xpridex.paymentapp.presentation.installmentselector

import com.xpridex.paymentapp.core.mvi.MviReducer
import com.xpridex.paymentapp.presentation.installmentselector.InstallmentSelectorResult.*
import javax.inject.Inject

class InstallmentSelectorReducer @Inject constructor() :
    MviReducer<InstallmentSelectorUiState, InstallmentSelectorResult> {

    override fun InstallmentSelectorUiState.reduceWith(result: InstallmentSelectorResult): InstallmentSelectorUiState {
        val previousState = this
        return when (result) {
            GetInstallmentsResult.InProgress -> previousState.copy(
                isLoading = true
            )
            is GetInstallmentsResult.Success -> previousState.copy(
                isLoading = false,
                recommendedMessages = result.recommendedMessages
            )
            GetInstallmentsResult.Empty -> previousState.copy(isEmpty = true)
            GetInstallmentsResult.Error -> previousState.copy(isEmpty = true)
            is SaveInstallmentResult.NavigateToHome -> previousState
        }
    }
}
