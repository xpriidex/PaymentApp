package com.xpridex.paymentapp.ui.installments


import com.xpridex.paymentapp.presentation.InstallmentSelectorViewModel
import com.xpridex.paymentapp.presentation.installmentselector.InstallmentSelectorUIntent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class InstallmentSelectorIntentHandler @Inject constructor() {

    var viewModel: InstallmentSelectorViewModel? = null

    fun initialUIntent() {
        viewModel?.processUserIntents(InstallmentSelectorUIntent.InitialUIntent)
    }

    fun selectInstallment(recommendedMessage: String) {
        viewModel?.processUserIntents(InstallmentSelectorUIntent.SelectInstallment(recommendedMessage))
    }
}
