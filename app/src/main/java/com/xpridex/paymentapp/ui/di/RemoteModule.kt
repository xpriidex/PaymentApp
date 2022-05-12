package com.xpridex.paymentapp.ui.di


import com.xpridex.paymentapp.data.remote.PaymentsRemoteImpl
import com.xpridex.paymentapp.data.remote.api.PaymentsApi
import com.xpridex.paymentapp.data.source.PaymentsRemote
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteModule {
    @Singleton
    @Provides
    fun providesPaymentsRemote(paymentsApi: PaymentsApi): PaymentsRemote =
        PaymentsRemoteImpl(paymentsApi)

}
