package com.xpridex.paymentapp.presentation.installmentselector

import com.xpridex.paymentapp.core.execution.ExecutionThread
import com.xpridex.paymentapp.domain.GetRecommendedMessagesUseCase
import com.xpridex.paymentapp.domain.SaveRecommendedMessageUseCase
import com.xpridex.paymentapp.presentation.installmentselector.InstallmentSelectorResult.GetInstallmentsResult
import com.xpridex.paymentapp.presentation.installmentselector.InstallmentSelectorResult.SaveInstallmentResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class InstallmentSelectorProcessor @Inject constructor(
    private val getRecommendedMessagesUseCase: GetRecommendedMessagesUseCase,
    private val saveRecommendedMessageUseCase: SaveRecommendedMessageUseCase,
    private val coroutineThreadProvider: ExecutionThread
) {
    fun actionProcessor(actions: InstallmentSelectorAction): Flow<InstallmentSelectorResult> =
        when (actions) {
            InstallmentSelectorAction.GetInstallmentsAction -> getInstallmentsProcessor()
            is InstallmentSelectorAction.SaveInstallmentAction -> saveInstallmentProcessor(
                recommendedMessage = actions.recommendedMessage
            )
        }

    private fun getInstallmentsProcessor() = getRecommendedMessagesUseCase.invoke().map {
        GetInstallmentsResult.Success(it) as InstallmentSelectorResult
    }.onStart {
        emit(GetInstallmentsResult.InProgress)
    }.catch {
        emit(GetInstallmentsResult.Error)
    }
        .flowOn(coroutineThreadProvider.ioThread())

    private fun saveInstallmentProcessor(recommendedMessage: String): Flow<SaveInstallmentResult> =
        flow {
            saveRecommendedMessageUseCase.invoke(recommendedMessage)
            emit(SaveInstallmentResult.NavigateToHome)
        }
}
