package com.xpridex.paymentapp.presentation.installmentselector

import androidx.compose.runtime.Immutable
import com.xpridex.paymentapp.core.mvi.events.MviUiState

@Immutable
data class InstallmentSelectorUiState(
    val isLoading: Boolean,
    val recommendedMessages: List<String>,
    val isEmpty: Boolean
) : MviUiState
