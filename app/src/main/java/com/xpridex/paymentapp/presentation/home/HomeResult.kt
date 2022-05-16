package com.xpridex.paymentapp.presentation.home

import com.xpridex.paymentapp.core.mvi.events.MviResult
import com.xpridex.paymentapp.presentation.home.model.Payment

sealed class HomeResult : MviResult {

    sealed class GetPaymentsResult : HomeResult() {
        object InProgress : GetPaymentsResult()
        data class Success(val payments: List<Payment>) : GetPaymentsResult()
        object Error : GetPaymentsResult()
        object Empty : GetPaymentsResult()
    }
}
