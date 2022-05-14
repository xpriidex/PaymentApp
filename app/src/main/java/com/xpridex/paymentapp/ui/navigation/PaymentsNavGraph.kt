package com.xpridex.paymentapp.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.xpridex.paymentapp.presentation.AmountViewModel
import com.xpridex.paymentapp.presentation.PaymentMethodViewModel
import com.xpridex.paymentapp.ui.amount.AmountIntentHandler
import com.xpridex.paymentapp.ui.paymentmethod.PaymentMethodIntentHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@ExperimentalMaterialApi
@FlowPreview
@ExperimentalCoroutinesApi
@ExperimentalAnimationApi
@Composable
fun PaymentsNavGraph(
    startDestination: String = PaymentsRoutes.Amount.path
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

    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        amountNav(
            viewModel = amountViewModel,
            intentHandler = amountIntentHandler
        )
        paymentMethodSelectorNav(
            onBack = { navActions.upPress() },
            viewModel = paymentMethodViewModel,
            intentHandler = paymentMethodIntentHandler
        )
        installmentSelectorNav()
    }
}
