package com.xpridex.paymentapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xpridex.paymentapp.core.mvi.MviPresentation
import com.xpridex.paymentapp.presentation.installmentselector.InstallmentSelectorAction
import com.xpridex.paymentapp.presentation.installmentselector.InstallmentSelectorAction.GetInstallmentsAction
import com.xpridex.paymentapp.presentation.installmentselector.InstallmentSelectorAction.SaveInstallmentAction
import com.xpridex.paymentapp.presentation.installmentselector.InstallmentSelectorProcessor
import com.xpridex.paymentapp.presentation.installmentselector.InstallmentSelectorReducer
import com.xpridex.paymentapp.presentation.installmentselector.InstallmentSelectorResult
import com.xpridex.paymentapp.presentation.installmentselector.InstallmentSelectorResult.SaveInstallmentResult.NavigateToHome
import com.xpridex.paymentapp.presentation.installmentselector.InstallmentSelectorUIntent
import com.xpridex.paymentapp.presentation.installmentselector.InstallmentSelectorUIntent.InitialUIntent
import com.xpridex.paymentapp.presentation.installmentselector.InstallmentSelectorUIntent.SelectInstallment
import com.xpridex.paymentapp.presentation.installmentselector.InstallmentSelectorUiState
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
class InstallmentSelectorViewModel @Inject constructor(
    private val reducer: InstallmentSelectorReducer,
    private val processor: InstallmentSelectorProcessor
) : ViewModel(), MviPresentation<InstallmentSelectorUIntent, InstallmentSelectorUiState> {

    var navActions: PaymentsNavActions? = null

    val defaultUiState: InstallmentSelectorUiState = InstallmentSelectorUiState(
        isLoading = false,
        recommendedMessages = emptyList(),
        isEmpty = false,
    )
    private val uiState = MutableStateFlow(defaultUiState)

    override fun processUserIntents(userIntent: InstallmentSelectorUIntent) {
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

    private fun InstallmentSelectorUIntent.toAction(): InstallmentSelectorAction {
        return when (this) {
            InitialUIntent -> GetInstallmentsAction
            is SelectInstallment -> SaveInstallmentAction(
                recommendedMessage = recommendedMessage
            )
        }
    }

    override fun uiStates(): StateFlow<InstallmentSelectorUiState> = uiState


    private fun Flow<InstallmentSelectorResult>.checkResultForNav(): Flow<InstallmentSelectorResult> =
        onEach { result ->
            when (result) {
                NavigateToHome -> navActions?.home?.invoke()
                else -> return@onEach
            }
        }
}