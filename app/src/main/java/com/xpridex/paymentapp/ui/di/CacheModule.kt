package com.xpridex.paymentapp.ui.di


import android.content.Context
import androidx.room.Room
import com.xpridex.paymentapp.data.cache.PaymentsCacheImpl
import com.xpridex.paymentapp.data.cache.database.PaymentsDataBase
import com.xpridex.paymentapp.data.cache.database.dao.PaymentsDao
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

    @Provides
    @Singleton
    fun provideBlogDatabase(
        @ApplicationContext context: Context
    ): PaymentsDataBase =
        Room.databaseBuilder(context, PaymentsDataBase::class.java, "payments.db")
            .build()

    @Provides
    @Singleton
    fun provideUserDao(database: PaymentsDataBase): PaymentsDao =
        database.paymentsDao()

    @Singleton
    @Provides
    fun providePaymentsDataStoreBuilder(@ApplicationContext appContext: Context): PaymentsDataStoreBuilder =
        PaymentsDataStoreBuilder(appContext)

    @Singleton
    @Provides
    fun providesPaymentsCache(
        dataStore: PaymentsDataStore,
        paymentsDao: PaymentsDao
    ): PaymentsCache =
        PaymentsCacheImpl(dataStore, paymentsDao)
}
