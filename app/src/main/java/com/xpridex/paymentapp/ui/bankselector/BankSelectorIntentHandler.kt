package com.xpridex.paymentapp.ui.bankselector

import com.xpridex.paymentapp.presentation.BankSelectorViewModel
import com.xpridex.paymentapp.presentation.bankselector.BankSelectorUIntent.InitialUIntent
import com.xpridex.paymentapp.presentation.bankselector.BankSelectorUIntent.SelectBank
import com.xpridex.paymentapp.presentation.bankselector.model.Bank
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
class BankSelectorIntentHandler @Inject constructor() {

    var viewModel: BankSelectorViewModel? = null

    fun initialUIntent() {
        viewModel?.processUserIntents(InitialUIntent)
    }

    fun selectBank(bank: Bank) {
        viewModel?.processUserIntents(SelectBank(bank))
    }
}
