package com.xpridex.paymentapp.presentation.paymentmethod

import com.xpridex.paymentapp.core.execution.ExecutionThread
import com.xpridex.paymentapp.domain.GetCreditCardPaymentMethodsUseCase
import com.xpridex.paymentapp.domain.SavePaymentMethodUseCase
import com.xpridex.paymentapp.presentation.paymentmethod.PaymentMethodAction.GetPaymentMethodsAction
import com.xpridex.paymentapp.presentation.paymentmethod.PaymentMethodAction.SavePaymentMethodAction
import com.xpridex.paymentapp.presentation.paymentmethod.PaymentMethodResult.GetPaymentMethodsResult
import com.xpridex.paymentapp.presentation.paymentmethod.PaymentMethodResult.SavePaymentMethodResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class PaymentMethodProcessor @Inject constructor(
    private val getCreditCardPaymentMethodsUseCase: GetCreditCardPaymentMethodsUseCase,
    private val savePaymentMethodUseCase: SavePaymentMethodUseCase,
    private val coroutineThreadProvider: ExecutionThread
) {
    fun actionProcessor(actions: PaymentMethodAction): Flow<PaymentMethodResult> =
        when (actions) {
            GetPaymentMethodsAction -> getPaymentMethodsProcessor()
            is SavePaymentMethodAction -> savePaymentMethodProcessor(paymentMethod = actions.paymentMethod)
        }

    private fun getPaymentMethodsProcessor() = getCreditCardPaymentMethodsUseCase.invoke().map {
        println("**** GetPaymentMethodsResultSuccess")
        GetPaymentMethodsResult.Success(it) as PaymentMethodResult
    }.onStart {
        emit(GetPaymentMethodsResult.InProgress)
    }.catch {
        emit(GetPaymentMethodsResult.Error)
    }
        .flowOn(coroutineThreadProvider.ioThread())

    private fun savePaymentMethodProcessor(paymentMethod: String): Flow<SavePaymentMethodResult> =
        flow {
            savePaymentMethodUseCase.invoke(paymentMethod)
            emit(SavePaymentMethodResult.NavigateToBankSelector)
        }
}
