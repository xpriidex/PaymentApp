package com.xpridex.paymentapp.presentation.installmentselector

import androidx.compose.runtime.Immutable
import com.xpridex.paymentapp.core.mvi.events.MviUiState
import com.xpridex.paymentapp.data.remote.model.BankApiModel
import com.xpridex.paymentapp.data.remote.model.PayerCostApiModel

@Immutable
data class InstallmentSelectorUiState(
    val isLoading: Boolean,
    val recommendedMessages: List<String>,
    val isEmpty: Boolean
) : MviUiState
