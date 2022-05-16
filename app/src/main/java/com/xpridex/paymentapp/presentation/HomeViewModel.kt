package com.xpridex.paymentapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xpridex.paymentapp.core.mvi.MviPresentation
import com.xpridex.paymentapp.presentation.home.HomeAction
import com.xpridex.paymentapp.presentation.home.HomeAction.GetPaymentsAction
import com.xpridex.paymentapp.presentation.home.HomeProcessor
import com.xpridex.paymentapp.presentation.home.HomeReducer
import com.xpridex.paymentapp.presentation.home.HomeUIntent
import com.xpridex.paymentapp.presentation.home.HomeUIntent.InitialUIntent
import com.xpridex.paymentapp.presentation.home.HomeUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.scan
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val reducer: HomeReducer,
    private val processor: HomeProcessor
) : ViewModel(), MviPresentation<HomeUIntent, HomeUiState> {

    val defaultUiState: HomeUiState = HomeUiState(
        isLoading = false,
        payments = emptyList(),
        isEmpty = false,
    )
    private val uiState = MutableStateFlow(defaultUiState)

    override fun processUserIntents(userIntent: HomeUIntent) {
        processor.actionProcessor(userIntent.toAction())
            .scan(defaultUiState) { previousUiState, result ->
                with(reducer) { previousUiState reduceWith result }
            }
            .onEach { uiState ->
                this.uiState.value = uiState
            }
            .launchIn(viewModelScope)
    }

    private fun HomeUIntent.toAction(): HomeAction {
        return when (this) {
            InitialUIntent -> GetPaymentsAction
        }
    }

    override fun uiStates(): StateFlow<HomeUiState> = uiState
}