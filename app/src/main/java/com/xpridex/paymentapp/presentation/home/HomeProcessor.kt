package com.xpridex.paymentapp.presentation.home

import com.xpridex.paymentapp.core.execution.ExecutionThread
import com.xpridex.paymentapp.data.PaymentsRepository
import com.xpridex.paymentapp.presentation.home.HomeAction.GetPaymentsAction
import com.xpridex.paymentapp.presentation.home.HomeResult.GetPaymentsResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class HomeProcessor @Inject constructor(
    private val repository: PaymentsRepository,
    private val coroutineThreadProvider: ExecutionThread
) {
    fun actionProcessor(actions: HomeAction): Flow<HomeResult> =
        when (actions) {
            GetPaymentsAction -> getPaymentsProcessor()
        }

    private fun getPaymentsProcessor() = repository.getPayments().map {
        GetPaymentsResult.Success(it) as HomeResult
    }.onStart {
        emit(GetPaymentsResult.InProgress)
    }.catch {
        emit(GetPaymentsResult.Error)
    }
        .flowOn(coroutineThreadProvider.ioThread())
}
