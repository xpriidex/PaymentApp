package com.xpridex.paymentapp.ui.di


import android.content.Context
import com.xpridex.paymentapp.data.cache.PaymentsCacheImpl
import com.xpridex.paymentapp.data.cache.datastore.PaymentsDataStore
import com.xpridex.paymentapp.data.cache.datastore.config.PaymentsDataStoreBuilder
import com.xpridex.paymentapp.data.source.PaymentsCache
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class CacheModule {

    @Singleton
    @Provides
    fun providePaymentsDataStoreBuilder(@ApplicationContext appContext: Context): PaymentsDataStoreBuilder =
        PaymentsDataStoreBuilder(appContext)


    @Singleton
    @Provides
    fun providesPaymentsCache(dataStore: PaymentsDataStore): PaymentsCache =
        PaymentsCacheImpl(dataStore)
}
