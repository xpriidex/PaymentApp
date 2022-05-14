package com.xpridex.paymentapp.data.cache.datastore

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.xpridex.paymentapp.data.cache.datastore.config.PaymentsDataStoreBuilder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PaymentsDataStore @Inject constructor(
    private val dataStoreBuilder: PaymentsDataStoreBuilder
) {
    private val amountKey = stringPreferencesKey(AMOUNT)

    suspend fun saveAmount(amount: String) {
        with(dataStoreBuilder) {
            getDataStore.edit { preferences ->
                preferences[amountKey] = amount
            }
        }
    }

    fun getAmount(): Flow<String> = with(dataStoreBuilder) {
        getDataStore.data.map { preferences ->
            val amount = preferences[amountKey].orEmpty()
            amount
        }
    }

    suspend fun clear() {
        with(dataStoreBuilder) {
            getDataStore.edit { preferences ->
                preferences.clear()
            }
        }
    }

    companion object {
        const val AMOUNT = "amount"
    }
}
