package com.xpridex.paymentapp.presentation.installmentselector

import com.xpridex.paymentapp.core.mvi.events.MviResult

sealed class InstallmentSelectorResult : MviResult {

    sealed class GetInstallmentsResult : InstallmentSelectorResult() {
        object InProgress : GetInstallmentsResult()
        data class Success(val recommendedMessages: List<String>) : GetInstallmentsResult()
        object Error : GetInstallmentsResult()
        object Empty : GetInstallmentsResult()
    }

    sealed class SaveInstallmentResult : InstallmentSelectorResult() {
        object NavigateToHome : SaveInstallmentResult()
    }
}
