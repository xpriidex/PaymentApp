package com.xpridex.paymentapp.core.mvi

import com.xpridex.paymentapp.core.mvi.events.MviEffect

interface MviUiEffect<in TUiEffect : MviEffect> {
    fun handleEffect(uiEffect: TUiEffect)
}