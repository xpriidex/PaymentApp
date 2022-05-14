package com.xpridex.paymentapp.presentation.bankselector

import com.xpridex.paymentapp.core.execution.ExecutionThread
import com.xpridex.paymentapp.data.PaymentsRepository
import com.xpridex.paymentapp.presentation.bankselector.BankSelectorAction.GetBanksAction
import com.xpridex.paymentapp.presentation.bankselector.BankSelectorAction.SaveBankAction
import com.xpridex.paymentapp.presentation.bankselector.BankSelectorResult.GetBanksResult
import com.xpridex.paymentapp.presentation.bankselector.BankSelectorResult.SaveBankResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class BankSelectorProcessor @Inject constructor(
    private val repository: PaymentsRepository,
    private val coroutineThreadProvider: ExecutionThread
) {
    fun actionProcessor(actions: BankSelectorAction): Flow<BankSelectorResult> =
        when (actions) {
            GetBanksAction -> getBanksProcessor()
            is SaveBankAction -> saveBankProcessor(bank = actions.bank)
        }

    private fun getBanksProcessor() = repository.getBanks().map {
        GetBanksResult.Success(it) as BankSelectorResult
    }.onStart {
        emit(GetBanksResult.InProgress)
    }.catch {
        emit(GetBanksResult.Error)
    }
        .flowOn(coroutineThreadProvider.ioThread())

    private fun saveBankProcessor(bank: String): Flow<SaveBankResult> =
        flow {
            repository.saveBank(bank)
            emit(SaveBankResult.NavigateToInstallments)
        }
}
