package com.xpridex.paymentapp.presentation.home

import androidx.compose.runtime.Immutable
import com.xpridex.paymentapp.core.mvi.events.MviUiState
import com.xpridex.paymentapp.data.cache.database.model.PaymentEntity
import com.xpridex.paymentapp.data.remote.model.BankApiModel
import com.xpridex.paymentapp.data.remote.model.PayerCostApiModel

@Immutable
data class HomeUiState(
    val isLoading: Boolean,
    val payments: List<PaymentEntity>,
    val isEmpty: Boolean
) : MviUiState
