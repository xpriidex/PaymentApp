package com.xpridex.paymentapp.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.xpridex.paymentapp.presentation.AmountViewModel
import com.xpridex.paymentapp.ui.amount.AmountIntentHandler
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

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

    AnimatedNavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        amountNav(
            viewModel = amountViewModel,
            intentHandler = amountIntentHandler
        )
        methodSelectorNav()
        installmentSelectorNav()
    }
}
