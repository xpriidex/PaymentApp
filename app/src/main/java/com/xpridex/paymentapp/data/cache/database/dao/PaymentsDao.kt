package com.xpridex.paymentapp.data.cache.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.xpridex.paymentapp.data.cache.database.model.Constants.PAYMENTS
import com.xpridex.paymentapp.data.cache.database.model.PaymentEntity

@Dao
 interface PaymentsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(paymentEntity: PaymentEntity)

    @Query("SELECT * FROM $PAYMENTS")
    suspend fun getAll(): List<PaymentEntity>

}
