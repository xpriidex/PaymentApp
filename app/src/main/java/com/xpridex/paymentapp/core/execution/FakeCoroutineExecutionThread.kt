package com.xpridex.paymentapp.core.execution

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

class FakeCoroutineExecutionThread : CoroutineExecutionThread {

    override fun uiThread(): CoroutineDispatcher = Dispatchers.Main

    override fun ioThread(): CoroutineDispatcher = Dispatchers.IO

}