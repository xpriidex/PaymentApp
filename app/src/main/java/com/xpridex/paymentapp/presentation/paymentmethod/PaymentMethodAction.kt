package com.xpridex.paymentapp.presentation.paymentmethod

import com.xpridex.paymentapp.core.mvi.events.MviAction


sealed class PaymentMethodAction : MviAction {
    object GetPaymentMethodsAction : PaymentMethodAction()
    data class SavePaymentMethodAction(val paymentMethod: String) : PaymentMethodAction()
}
