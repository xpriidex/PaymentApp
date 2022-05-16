package com.xpridex.paymentapp.presentation.bankselector

import androidx.compose.runtime.Immutable
import com.xpridex.paymentapp.core.mvi.events.MviUiState
import com.xpridex.paymentapp.presentation.bankselector.model.Bank

@Immutable
data class BankSelectorUiState(
    val isLoading: Boolean,
    val amount: String,
    val banks: List<Bank>,
    val isEmpty: Boolean
) : MviUiState
