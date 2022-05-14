package com.xpridex.paymentapp.ui.paymentmethod

import com.xpridex.paymentapp.presentation.PaymentMethodViewModel
import com.xpridex.paymentapp.presentation.paymentmethod.PaymentMethodUIntent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class PaymentMethodIntentHandler @Inject constructor() {

    var viewModel: PaymentMethodViewModel? = null

    fun initialUIntent() {
        viewModel?.processUserIntents(PaymentMethodUIntent.InitialUIntent)
    }

    fun continueUIntentUIntent() {
        viewModel?.processUserIntents(PaymentMethodUIntent.ContinueUIntent)
    }
}
