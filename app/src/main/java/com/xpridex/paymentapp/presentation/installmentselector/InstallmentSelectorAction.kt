package com.xpridex.paymentapp.presentation.installmentselector

import com.xpridex.paymentapp.core.mvi.events.MviAction


sealed class InstallmentSelectorAction : MviAction {
    object GetInstallmentsAction : InstallmentSelectorAction()
    data class SaveInstallmentAction(val recommendedMessage: String) : InstallmentSelectorAction()
}
