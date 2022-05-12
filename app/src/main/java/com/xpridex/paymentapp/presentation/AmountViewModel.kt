package com.xpridex.paymentapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xpridex.paymentapp.core.mvi.MviPresentation
import com.xpridex.paymentapp.presentation.amount.AmountAction
import com.xpridex.paymentapp.presentation.amount.AmountAction.SaveAmountAction
import com.xpridex.paymentapp.presentation.amount.AmountProcessor
import com.xpridex.paymentapp.presentation.amount.AmountReducer
import com.xpridex.paymentapp.presentation.amount.AmountResult
import com.xpridex.paymentapp.presentation.amount.AmountResult.SaveAmountResult.*
import com.xpridex.paymentapp.presentation.amount.AmountUIntent
import com.xpridex.paymentapp.presentation.amount.AmountUIntent.ContinueUIntent
import com.xpridex.paymentapp.presentation.amount.AmountUiState
import com.xpridex.paymentapp.ui.navigation.PaymentsNavActions
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan
import javax.inject.Inject

@HiltViewModel
class AmountViewModel @Inject constructor(
    private val reducer: AmountReducer,
    private val processor: AmountProcessor
) : ViewModel(), MviPresentation<AmountUIntent, AmountUiState> {

    val defaultUiState: AmountUiState = AmountUiState.DisplayAmountInputState
    private val uiState = MutableStateFlow(defaultUiState)

    var navActions: PaymentsNavActions? = null

    override fun processUserIntents(userIntent: AmountUIntent) {
        processor.actionProcessor(userIntent.toAction())
            .checkResultForNav()
            .scan(defaultUiState) { previousUiState, result ->
                with(reducer) { previousUiState reduceWith result }
            }
            .onEach { uiState ->
                this.uiState.value = uiState
            }
            .launchIn(viewModelScope)
    }

    private fun AmountUIntent.toAction(): AmountAction {
        return when (this) {
            is ContinueUIntent -> SaveAmountAction(amount = amount)
        }
    }

    override fun uiStates(): StateFlow<AmountUiState> = uiState


     private fun Flow<AmountResult>.checkResultForNav(): Flow<AmountResult> =
         onEach { result ->
             when (result) {
                 NavigateToPaymentMethods -> navActions?.paymentMethods?.invoke()
                 else -> return@onEach
             }
         }
}