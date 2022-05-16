package com.xpridex.paymentapp.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.xpridex.paymentapp.presentation.AmountViewModel
import com.xpridex.paymentapp.presentation.BankSelectorViewModel
import com.xpridex.paymentapp.presentation.HomeViewModel
import com.xpridex.paymentapp.presentation.InstallmentSelectorViewModel
import com.xpridex.paymentapp.presentation.PaymentMethodViewModel
import com.xpridex.paymentapp.ui.amount.AmountIntentHandler
import com.xpridex.paymentapp.ui.amount.AmountScreen
import com.xpridex.paymentapp.ui.bankselector.BankSelectorIntentHandler
import com.xpridex.paymentapp.ui.bankselector.BankSelectorScreen
import com.xpridex.paymentapp.ui.home.HomeIntentHandler
import com.xpridex.paymentapp.ui.home.HomeScreen
import com.xpridex.paymentapp.ui.installments.InstallmentSelectorIntentHandler
import com.xpridex.paymentapp.ui.installments.InstallmentSelectorScreen
import com.xpridex.paymentapp.ui.paymentmethod.PaymentMethodIntentHandler
import com.xpridex.paymentapp.ui.paymentmethod.PaymentMethodSelectorScreen

@ExperimentalMaterialApi
@ExperimentalAnimationApi
internal fun NavGraphBuilder.homeNav(
    onPaymentEvent: () -> Unit,
    viewModel: HomeViewModel,
    intentHandler: HomeIntentHandler,
) = composable(
    route = PaymentsRoutes.Home.path,
    enterTransition = { enterTransition },
    exitTransition = { exitTransition },
    popEnterTransition = { popEnterTransition },
    popExitTransition = { popExitTransition }
) {

    val homeUiState = remember {
        viewModel.uiStates()
    }.collectAsState(initial = viewModel.defaultUiState)

    intentHandler.initialUIntent()

    HomeScreen(
        onPaymentEvent = onPaymentEvent,
        uiState = homeUiState
    )
}

@ExperimentalComposeUiApi
@ExperimentalAnimationApi
internal fun NavGraphBuilder.amountNav(
    onBackEvent: () -> Unit = {},
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
        onBackEvent = onBackEvent,
        onContinueEvent = { intentHandler.continueToMethodSelectorUIntent(it) },
        uiState = amountUiState
    )
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
internal fun NavGraphBuilder.paymentMethodSelectorNav(
    onBackEvent: () -> Unit = {},
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

    PaymentMethodSelectorScreen(
        onBackEvent = onBackEvent,
        selectPaymentEvent = { intentHandler.selectPaymentMethod(it) },
        uiState = paymentMethodUiState
    )
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
internal fun NavGraphBuilder.bankSelectorNav(
    onBackEvent: () -> Unit = {},
    viewModel: BankSelectorViewModel,
    intentHandler: BankSelectorIntentHandler
) = composable(
    route = PaymentsRoutes.BankSelector.path,
    enterTransition = { enterTransition },
    exitTransition = { exitTransition },
    popEnterTransition = { popEnterTransition },
    popExitTransition = { popExitTransition }
) {

    val bankUiState = remember {
        viewModel.uiStates()
    }.collectAsState(initial = viewModel.defaultUiState)

    intentHandler.initialUIntent()

    BankSelectorScreen(
        onBackEvent = onBackEvent,
        selectBankEvent = { intentHandler.selectBank(it) },
        uiState = bankUiState
    )
}

@ExperimentalMaterialApi
@ExperimentalAnimationApi
internal fun NavGraphBuilder.installmentSelectorNav(
    onBackEvent: () -> Unit,
    viewModel: InstallmentSelectorViewModel,
    intentHandler: InstallmentSelectorIntentHandler
) = composable(
    route = PaymentsRoutes.InstallmentSelector.path,
    enterTransition = { enterTransition },
    exitTransition = { exitTransition },
    popEnterTransition = { popEnterTransition },
    popExitTransition = { popExitTransition }
) {

    val installmentSelectorUiState = remember {
        viewModel.uiStates()
    }.collectAsState(initial = viewModel.defaultUiState)

    intentHandler.initialUIntent()

    InstallmentSelectorScreen(
        onBackEvent = onBackEvent,
        selectInstallmentEvent = { intentHandler.selectInstallment(it) },
        uiState = installmentSelectorUiState
    )
}

