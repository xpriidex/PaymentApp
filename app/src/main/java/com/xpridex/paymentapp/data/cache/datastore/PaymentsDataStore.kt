package com.xpridex.paymentapp.data.cache.datastore

import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.xpridex.paymentapp.data.cache.datastore.config.PaymentsDataStoreBuilder
import com.xpridex.paymentapp.data.cache.datastore.model.BankInfoCache
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PaymentsDataStore @Inject constructor(
    private val dataStoreBuilder: PaymentsDataStoreBuilder
) {
    private val amountKey = stringPreferencesKey(AMOUNT)
    private val paymentMethodKey = stringPreferencesKey(PAYMENT_METHOD)
    private val bankIdKey = stringPreferencesKey(BANK_ID)
    private val bankNameKey = stringPreferencesKey(BANK_NAME)

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

    suspend fun saveBank(id: String, name: String) {
        with(dataStoreBuilder) {
            getDataStore.edit { preferences ->
                preferences[bankIdKey] = id
                preferences[bankNameKey] = name
            }
        }
    }

    fun getBankId(): Flow<String> = with(dataStoreBuilder) {
        getDataStore.data.map { preferences ->
            val id = preferences[bankIdKey].orEmpty()
            id
        }
    }

    fun getBankInfo(): Flow<BankInfoCache> = with(dataStoreBuilder) {
        getDataStore.data.map { preferences ->
            val id = preferences[bankIdKey].orEmpty()
            val name = preferences[bankNameKey].orEmpty()
            BankInfoCache(id = id, name = name)
        }
    }


    companion object {
        const val AMOUNT = "amount"
        const val PAYMENT_METHOD = "payment_method"
        const val BANK_ID = "bank_id"
        const val BANK_NAME = "bank_name"
    }
}
