package com.xpridex.paymentapp.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.xpridex.paymentapp.presentation.AmountViewModel
import com.xpridex.paymentapp.presentation.BankSelectorViewModel
import com.xpridex.paymentapp.presentation.HomeViewModel
import com.xpridex.paymentapp.presentation.InstallmentSelectorViewModel
import com.xpridex.paymentapp.presentation.PaymentMethodViewModel
import com.xpridex.paymentapp.ui.amount.AmountIntentHandler
import com.xpridex.paymentapp.ui.bankselector.BankSelectorIntentHandler
import com.xpridex.paymentapp.ui.home.HomeIntentHandler
import com.xpridex.paymentapp.ui.installments.InstallmentSelectorIntentHandler
import com.xpridex.paymentapp.ui.paymentmethod.PaymentMethodIntentHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalMaterialApi
@FlowPreview
@ExperimentalCoroutinesApi
@ExperimentalAnimationApi
@Composable
fun PaymentsNavGraph(
    startDestination: String = PaymentsRoutes.Home.path
) {
    val navController = rememberAnimatedNavController()
    val navActions = remember(navController) { PaymentsNavActions(navController) }

    val amountViewModel = hiltViewModel<AmountViewModel>()
    amountViewModel.apply {
        this.navActions = navActions
    }

    val amountIntentHandler = AmountIntentHandler().apply {
        this.viewModel = amountViewModel
    }

    val paymentMethodViewModel = hiltViewModel<PaymentMethodViewModel>()
    paymentMethodViewModel.apply {
        this.navActions = navActions
    }

    val paymentMethodIntentHandler = PaymentMethodIntentHandler().apply {
        this.viewModel = paymentMethodViewModel
    }

    val bankSelectorViewModel = hiltViewModel<BankSelectorViewModel>()
    bankSelectorViewModel.apply {
        this.navActions = navActions
    }

    val bankSelectorIntentHandler = BankSelectorIntentHandler().apply {
        this.viewModel = bankSelectorViewModel
    }

    val installmentSelectorViewModel = hiltViewModel<InstallmentSelectorViewModel>()
    installmentSelectorViewModel.apply {
        this.navActions = navActions
    }

    val installmentSelectorIntentHandler = InstallmentSelectorIntentHandler().apply {
        this.viewModel = installmentSelectorViewModel
    }

    val homeViewModel = hiltViewModel<HomeViewModel>()

    val homeIntentHandler = HomeIntentHandler().apply {
        this.viewModel = homeViewModel
    }

    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        homeNav(
            onPaymentEvent = { navActions.amount() },
            viewModel = homeViewModel,
            intentHandler = homeIntentHandler
        )

        amountNav(
            onBackEvent = { navActions.upPress() },
            viewModel = amountViewModel,
            intentHandler = amountIntentHandler
        )
        paymentMethodSelectorNav(
            onBackEvent = { navActions.upPress() },
            viewModel = paymentMethodViewModel,
            intentHandler = paymentMethodIntentHandler
        )

        bankSelectorNav(
            onBackEvent = { navActions.upPress() },
            viewModel = bankSelectorViewModel,
            intentHandler = bankSelectorIntentHandler
        )
        installmentSelectorNav(
            onBackEvent = { navActions.upPress() },
            viewModel = installmentSelectorViewModel,
            intentHandler = installmentSelectorIntentHandler
        )
    }
}
