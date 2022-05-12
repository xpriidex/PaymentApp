package com.xpridex.paymentapp.ui.navigation

internal sealed class PaymentsRoutes(val path: String) {
    object Amount : PaymentsRoutes(path = "Amount")
    object PaymentMethodSelector : PaymentsRoutes(path = "MethodSelector")
    object InstallmentSelector : PaymentsRoutes(path = "InstallmentSelector")
}
