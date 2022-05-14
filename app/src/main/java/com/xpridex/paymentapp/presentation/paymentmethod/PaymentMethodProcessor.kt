package com.xpridex.paymentapp.presentation.paymentmethod

import com.xpridex.paymentapp.core.execution.ExecutionThread
import com.xpridex.paymentapp.domain.GetCreditCardPaymentMethodsUseCase
import com.xpridex.paymentapp.presentation.paymentmethod.PaymentMethodAction.GetPaymentMethodsAction
import com.xpridex.paymentapp.presentation.paymentmethod.PaymentMethodResult.GetPaymentMethodsResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class PaymentMethodProcessor @Inject constructor(
    private val useCase: GetCreditCardPaymentMethodsUseCase,
    private val coroutineThreadProvider: ExecutionThread
) {
    fun actionProcessor(actions: PaymentMethodAction): Flow<PaymentMethodResult> =
        when (actions) {
            is GetPaymentMethodsAction -> getPaymentMethodsProcessor()
        }

    private fun getPaymentMethodsProcessor() = useCase.invoke().map {
        GetPaymentMethodsResult.Success(it) as PaymentMethodResult
    }.onStart {
        emit(GetPaymentMethodsResult.InProgress)
    }.catch {
        emit(GetPaymentMethodsResult.Error)
    }
        .flowOn(coroutineThreadProvider.ioThread())

}
