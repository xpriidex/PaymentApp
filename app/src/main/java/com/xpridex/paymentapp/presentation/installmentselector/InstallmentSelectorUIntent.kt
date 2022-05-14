package com.xpridex.paymentapp.presentation.installmentselector

import com.xpridex.paymentapp.core.mvi.events.MviUserIntent

sealed class InstallmentSelectorUIntent : MviUserIntent {
    object InitialUIntent : InstallmentSelectorUIntent()
     data class SelectInstallment(val recommendedMessage: String) : InstallmentSelectorUIntent()
}
