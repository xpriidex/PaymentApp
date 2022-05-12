package com.xpridex.paymentapp.core.mvi

import com.xpridex.paymentapp.core.mvi.events.MviUiState
import com.xpridex.paymentapp.core.mvi.events.MviUserIntent
import kotlinx.coroutines.flow.Flow

interface MviUi<TUserIntent: MviUserIntent, in TUiState: MviUiState> {

    fun userIntents(): Flow<TUserIntent>

    fun renderUiStates(uiState: TUiState)
}