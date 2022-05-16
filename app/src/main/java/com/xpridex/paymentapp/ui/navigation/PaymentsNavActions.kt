package com.xpridex.paymentapp.ui.navigation

import androidx.navigation.NavHostController

class PaymentsNavActions(navHostController: NavHostController) {

    val amount: () -> Unit = {
        navHostController.navigate(PaymentsRoutes.Amount.path)
    }

    val paymentMethods: () -> Unit = {
        navHostController.navigate(PaymentsRoutes.PaymentMethodSelector.path)
    }

    val bankSelector: () -> Unit = {
        navHostController.navigate(PaymentsRoutes.BankSelector.path)
    }

    val installment: () -> Unit = {
        navHostController.navigate(PaymentsRoutes.InstallmentSelector.path)
    }

    val home: () -> Unit = {
        navHostController.navigate(PaymentsRoutes.Home.path)
    }

    val upPress: () -> Unit = {
        navHostController.navigateUp()
    }

    val popBackStack: () -> Unit = {
        navHostController.popBackStack()
    }
}
