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
    private val paymentMethodKey = stringPreferencesKey(PAYMENT_METHOD)
    private val bankKey = stringPreferencesKey(BANK)

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

    suspend fun savePaymentMethod(paymentMethod: String) {
        with(dataStoreBuilder) {
            getDataStore.edit { preferences ->
                preferences[paymentMethodKey] = paymentMethod
            }
        }
    }

    fun getPaymentMethod(): Flow<String> = with(dataStoreBuilder) {
        getDataStore.data.map { preferences ->
            val paymentMethod = preferences[paymentMethodKey].orEmpty()
            paymentMethod
        }
    }

    suspend fun saveBank(bank: String) {
        with(dataStoreBuilder) {
            getDataStore.edit { preferences ->
                preferences[bankKey] = bank
            }
        }
    }

    fun getBank(): Flow<String> = with(dataStoreBuilder) {
        getDataStore.data.map { preferences ->
            val bank = preferences[bankKey].orEmpty()
            bank
        }
    }


    companion object {
        const val AMOUNT = "amount"
        const val PAYMENT_METHOD = "payment_method"
        const val BANK = "bank"
    }
}
