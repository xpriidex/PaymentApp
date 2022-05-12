package com.xpridex.paymentapp.core.mvi

import com.xpridex.paymentapp.core.mvi.events.MviResult
import com.xpridex.paymentapp.core.mvi.events.MviUiState

class UnsupportedReduceException(state: MviUiState, result: MviResult) :
    RuntimeException("Cannot reduce state: $state with result: $result")