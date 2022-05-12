package com.xpridex.paymentapp.presentation.amount

import androidx.compose.runtime.Immutable
import com.xpridex.paymentapp.core.mvi.events.MviUiState

@Immutable
data class AmountUiState(val isEmpty: Boolean) : MviUiState
