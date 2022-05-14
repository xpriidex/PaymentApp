package com.xpridex.paymentapp.ui.paymentmethod

import com.xpridex.paymentapp.presentation.PaymentMethodViewModel
import com.xpridex.paymentapp.presentation.paymentmethod.PaymentMethodUIntent.InitialUIntent
import com.xpridex.paymentapp.presentation.paymentmethod.PaymentMethodUIntent.SelectPaymentMethod
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class PaymentMethodIntentHandler @Inject constructor() {

    var viewModel: PaymentMethodViewModel? = null

    fun initialUIntent() {
        viewModel?.processUserIntents(InitialUIntent)
    }

    fun selectPaymentMethod(paymentMethod: String) {
        viewModel?.processUserIntents(SelectPaymentMethod(paymentMethod))
    }
}
