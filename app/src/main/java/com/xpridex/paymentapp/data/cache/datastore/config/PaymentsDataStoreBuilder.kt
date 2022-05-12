package com.xpridex.paymentapp.data.cache.datastore.config

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import javax.inject.Inject

class PaymentsDataStoreBuilder @Inject constructor(context: Context) {

    companion object {
        const val NAME = "PaymentsDataStore"

        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
            name = NAME
        )
    }

    val getDataStore = context.dataStore
}
