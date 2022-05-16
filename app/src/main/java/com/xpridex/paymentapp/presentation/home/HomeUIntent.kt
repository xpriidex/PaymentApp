package com.xpridex.paymentapp.presentation.home

import com.xpridex.paymentapp.core.mvi.events.MviUserIntent

sealed class HomeUIntent : MviUserIntent {
    object InitialUIntent : HomeUIntent()
}
