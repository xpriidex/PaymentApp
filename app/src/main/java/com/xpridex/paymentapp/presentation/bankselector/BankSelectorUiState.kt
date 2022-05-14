package com.xpridex.paymentapp.presentation.bankselector

import androidx.compose.runtime.Immutable
import com.xpridex.paymentapp.core.mvi.events.MviUiState
import com.xpridex.paymentapp.data.remote.model.BankApiModel

@Immutable
data class BankSelectorUiState(
    val isLoading: Boolean,
    val amount: String,
    val banks: List<BankApiModel>,
    val isEmpty: Boolean
) : MviUiState
