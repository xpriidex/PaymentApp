package com.xpridex.paymentapp.presentation.home

import com.xpridex.paymentapp.core.mvi.events.MviResult
import com.xpridex.paymentapp.data.cache.database.model.PaymentEntity
import com.xpridex.paymentapp.data.remote.model.BankApiModel
import com.xpridex.paymentapp.data.remote.model.PayerCostApiModel

sealed class HomeResult : MviResult {

    sealed class GetPaymentsResult : HomeResult() {
        object InProgress : GetPaymentsResult()
        data class Success(val payments: List<PaymentEntity>) : GetPaymentsResult()
        object Error : GetPaymentsResult()
        object Empty : GetPaymentsResult()
    }
}
