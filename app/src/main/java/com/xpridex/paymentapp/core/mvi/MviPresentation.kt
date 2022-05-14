package com.xpridex.paymentapp.core.mvi

import com.xpridex.paymentapp.core.mvi.events.MviUiState
import com.xpridex.paymentapp.core.mvi.events.MviUserIntent
import kotlinx.coroutines.flow.StateFlow


interface MviPresentation<TUserIntent: MviUserIntent, TUiState: MviUiState> {

    fun processUserIntents(userIntent: TUserIntent)

    fun uiStates(): StateFlow<TUiState>
}