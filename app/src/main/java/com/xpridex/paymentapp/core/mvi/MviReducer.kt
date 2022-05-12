package com.xpridex.paymentapp.core.mvi

import com.xpridex.paymentapp.core.mvi.events.MviResult
import com.xpridex.paymentapp.core.mvi.events.MviUiState

interface MviReducer<TUiState : MviUiState, TResult: MviResult> {

    infix fun TUiState.reduceWith(result: TResult): TUiState
}