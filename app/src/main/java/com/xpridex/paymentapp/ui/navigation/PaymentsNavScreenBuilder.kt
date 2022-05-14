package com.xpridex.paymentapp.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.xpridex.paymentapp.presentation.AmountViewModel
import com.xpridex.paymentapp.presentation.PaymentMethodViewModel
import com.xpridex.paymentapp.ui.amount.AmountIntentHandler
import com.xpridex.paymentapp.ui.amount.AmountScreen
import com.xpridex.paymentapp.ui.installments.InstallmentSelectorScreen
import com.xpridex.paymentapp.ui.paymentmethod.PaymentMethodIntentHandler
import com.xpridex.paymentapp.ui.paymentmethod.PaymentMethodSelectorScreen

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

@ExperimentalMaterialApi
@ExperimentalAnimationApi
internal fun NavGraphBuilder.paymentMethodSelectorNav(
    onBack: () -> Unit = {},
    viewModel: PaymentMethodViewModel,
    intentHandler: PaymentMethodIntentHandler
) = composable(
    route = PaymentsRoutes.PaymentMethodSelector.path,
    enterTransition = { enterTransition },
    exitTransition = { exitTransition },
    popEnterTransition = { popEnterTransition },
    popExitTransition = { popExitTransition }
) {

    val paymentMethodUiState = remember {
        viewModel.uiStates()
    }.collectAsState(initial = viewModel.defaultUiState)

    intentHandler.initialUIntent()

    PaymentMethodSelectorScreen(onBack = onBack, uiState = paymentMethodUiState)
}

@ExperimentalAnimationApi
internal fun NavGraphBuilder.installmentSelectorNav() = composable(
    route = PaymentsRoutes.InstallmentSelector.path,
    enterTransition = { enterTransition },
    exitTransition = { exitTransition },
    popEnterTransition = { popEnterTransition },
    popExitTransition = { popExitTransition }
) {

    InstallmentSelectorScreen()
}

