package com.xpridex.paymentapp.presentation.home

import com.xpridex.paymentapp.core.mvi.MviReducer
import com.xpridex.paymentapp.presentation.home.HomeResult.GetPaymentsResult
import javax.inject.Inject

class HomeReducer @Inject constructor() :
    MviReducer<HomeUiState, HomeResult> {

    override fun HomeUiState.reduceWith(result: HomeResult): HomeUiState {
        val previousState = this
        return when (result) {
            GetPaymentsResult.InProgress -> previousState.copy(
                isLoading = true
            )
            is GetPaymentsResult.Success -> previousState.copy(
                isLoading = false,
                payments = result.payments
            )
            GetPaymentsResult.Empty -> previousState.copy(isEmpty = true)
            GetPaymentsResult.Error -> previousState.copy(isEmpty = true)
        }
    }
}
