package com.xpridex.paymentapp.presentation.paymentmethod

import androidx.compose.runtime.Immutable
import com.xpridex.paymentapp.core.mvi.events.MviUiState
import com.xpridex.paymentapp.data.remote.model.PaymentMethodApiModel
import com.xpridex.paymentapp.domain.model.PaymentMethod

@Immutable
data class PaymentMethodUiState(
    val isLoading: Boolean,
    val amount: String,
    val paymentMethods: List<PaymentMethod>,
    val isEmpty: Boolean
) : MviUiState
