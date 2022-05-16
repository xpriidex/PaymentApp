package com.xpridex.paymentapp.ui.navigation

internal sealed class PaymentsRoutes(val path: String) {
    object Home : PaymentsRoutes(path = "Home")
    object Amount : PaymentsRoutes(path = "Amount")
    object PaymentMethodSelector : PaymentsRoutes(path = "MethodSelector")
    object BankSelector : PaymentsRoutes(path = "BankSelector")
    object InstallmentSelector : PaymentsRoutes(path = "InstallmentSelector")
}
