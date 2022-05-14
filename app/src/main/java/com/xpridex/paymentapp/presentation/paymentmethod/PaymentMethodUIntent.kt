package com.xpridex.paymentapp.presentation.paymentmethod

import com.xpridex.paymentapp.core.mvi.events.MviUserIntent

 sealed class PaymentMethodUIntent : MviUserIntent {
    object InitialUIntent : PaymentMethodUIntent()
     object ContinueUIntent : PaymentMethodUIntent()
}
