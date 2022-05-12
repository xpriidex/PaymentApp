package com.xpridex.paymentapp.ui.amount

import com.xpridex.paymentapp.presentation.AmountViewModel
import com.xpridex.paymentapp.presentation.amount.AmountUIntent.ContinueUIntent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class AmountIntentHandler @Inject constructor() {

    var viewModel: AmountViewModel? = null

    fun continueToMethodSelectorUIntent(amount: String) {
        viewModel?.let { safeViewModel ->
            safeViewModel.processUserIntents(ContinueUIntent(amount))
        }
    }
}
