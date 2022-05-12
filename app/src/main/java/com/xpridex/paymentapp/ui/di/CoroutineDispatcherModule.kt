package com.xpridex.paymentapp.ui.di


import com.xpridex.paymentapp.core.execution.CoroutineExecutionThread
import com.xpridex.paymentapp.core.execution.ExecutionThread
import dagger.Module
import dagger.Provides
import dagger.Reusable
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class CoroutineDispatcherModule {

    @Reusable
    @Provides
    fun provideCoroutineDispatchers(): CoroutineExecutionThread {
        return ExecutionThread()
    }
}
