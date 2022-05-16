package com.xpridex.paymentapp.presentation.home

import androidx.compose.runtime.Immutable
import com.xpridex.paymentapp.core.mvi.events.MviUiState
import com.xpridex.paymentapp.presentation.home.model.Payment

@Immutable
data class HomeUiState(
    val isLoading: Boolean,
    val payments: List<Payment>,
    val isEmpty: Boolean
) : MviUiState
