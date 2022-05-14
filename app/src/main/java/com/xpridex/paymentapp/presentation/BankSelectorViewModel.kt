package com.xpridex.paymentapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xpridex.paymentapp.core.mvi.MviPresentation
import com.xpridex.paymentapp.presentation.bankselector.BankSelectorAction
import com.xpridex.paymentapp.presentation.bankselector.BankSelectorAction.GetBanksAction
import com.xpridex.paymentapp.presentation.bankselector.BankSelectorAction.SaveBankAction
import com.xpridex.paymentapp.presentation.bankselector.BankSelectorProcessor
import com.xpridex.paymentapp.presentation.bankselector.BankSelectorReducer
import com.xpridex.paymentapp.presentation.bankselector.BankSelectorResult
import com.xpridex.paymentapp.presentation.bankselector.BankSelectorResult.SaveBankResult.NavigateToInstallments
import com.xpridex.paymentapp.presentation.bankselector.BankSelectorUIntent
import com.xpridex.paymentapp.presentation.bankselector.BankSelectorUIntent.SelectBank
import com.xpridex.paymentapp.presentation.bankselector.BankSelectorUiState
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
class BankSelectorViewModel @Inject constructor(
    private val reducer: BankSelectorReducer,
    private val processor: BankSelectorProcessor
) : ViewModel(), MviPresentation<BankSelectorUIntent, BankSelectorUiState> {

    var navActions: PaymentsNavActions? = null

    val defaultUiState: BankSelectorUiState = BankSelectorUiState(
        isLoading = false,
        amount = "",
        banks = emptyList(),
        isEmpty = false,
    )
    private val uiState = MutableStateFlow(defaultUiState)

    override fun processUserIntents(userIntent: BankSelectorUIntent) {
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

    private fun BankSelectorUIntent.toAction(): BankSelectorAction {
        return when (this) {
            BankSelectorUIntent.InitialUIntent -> GetBanksAction
            is SelectBank -> SaveBankAction(bank = bank)
        }
    }

    override fun uiStates(): StateFlow<BankSelectorUiState> = uiState


    private fun Flow<BankSelectorResult>.checkResultForNav(): Flow<BankSelectorResult> =
        onEach { result ->
            when (result) {
                NavigateToInstallments -> navActions?.installment?.invoke()
                else -> return@onEach
            }
        }
}