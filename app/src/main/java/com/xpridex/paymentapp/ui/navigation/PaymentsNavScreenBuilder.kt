package com.xpridex.paymentapp.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.xpridex.paymentapp.presentation.AmountViewModel
import com.xpridex.paymentapp.ui.amount.AmountIntentHandler
import com.xpridex.paymentapp.ui.amount.AmountScreen
import com.xpridex.paymentapp.ui.installmentsselector.InstallmentSelectorScreen
import com.xpridex.paymentapp.ui.methodselector.MethodSelectorScreen

@ExperimentalAnimationApi
internal fun NavGraphBuilder.amountNav(
    viewModel: AmountViewModel,
    intentHandler: AmountIntentHandler
) = composable(
    route = PaymentsRoutes.Amount.path,
    enterTransition = { enterTransition },
    exitTransition = { exitTransition },
    popEnterTransition = { popEnterTransition },
    popExitTransition = { popExitTransition }
) {

    val amountUiState = remember {
        viewModel.uiStates()
    }.collectAsState(initial = viewModel.defaultUiState)

    AmountScreen(
        onContinueEvent = { intentHandler.continueToMethodSelectorUIntent(it) },
        uiState = amountUiState
    )
}

@ExperimentalAnimationApi
internal fun NavGraphBuilder.methodSelectorNav() = composable(
    route = PaymentsRoutes.PaymentMethodSelector.path,
    enterTransition = { enterTransition },
    exitTransition = { exitTransition },
    popEnterTransition = { popEnterTransition },
    popExitTransition = { popExitTransition }
) {

    //val onboardingUiState = remember {
    //    viewModel.processUserIntentsAndObserveUiStates(
    //        userIntents = intentHandler.userIntents()
    //    )
    //}.collectAsState(initial = viewModel.defaultUiState)

    MethodSelectorScreen()
}

@ExperimentalAnimationApi
internal fun NavGraphBuilder.installmentSelectorNav() = composable(
    route = PaymentsRoutes.InstallmentSelector.path,
    enterTransition = { enterTransition },
    exitTransition = { exitTransition },
    popEnterTransition = { popEnterTransition },
    popExitTransition = { popExitTransition }
) {

    //val onboardingUiState = remember {
    //    viewModel.processUserIntentsAndObserveUiStates(
    //        userIntents = intentHandler.userIntents()
    //    )
    //}.collectAsState(initial = viewModel.defaultUiState)

    InstallmentSelectorScreen()
}

