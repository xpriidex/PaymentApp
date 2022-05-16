package com.xpridex.paymentapp.ui.home

import com.xpridex.paymentapp.presentation.HomeViewModel
import com.xpridex.paymentapp.presentation.home.HomeUIntent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class HomeIntentHandler @Inject constructor() {

    var viewModel: HomeViewModel? = null

    fun initialUIntent() {
        viewModel?.processUserIntents(HomeUIntent.InitialUIntent)
    }
}
