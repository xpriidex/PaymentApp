package com.xpridex.paymentapp.presentation.home

import com.xpridex.paymentapp.core.mvi.events.MviAction

sealed class HomeAction : MviAction {
    object GetPaymentsAction : HomeAction()
}
