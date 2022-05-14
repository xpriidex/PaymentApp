package com.xpridex.paymentapp.data.cache.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.xpridex.paymentapp.data.cache.database.model.Constants.AMOUNT
import com.xpridex.paymentapp.data.cache.database.model.Constants.ISSUER_ID
import com.xpridex.paymentapp.data.cache.database.model.Constants.PAYMENTS
import com.xpridex.paymentapp.data.cache.database.model.Constants.PAYMENTS_ID
import com.xpridex.paymentapp.data.cache.database.model.Constants.PAYMENT_METHOD
import com.xpridex.paymentapp.data.cache.database.model.Constants.RECOMMENDED_MESSAGE

@Entity(tableName = PAYMENTS)
 data class PaymentEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = PAYMENTS_ID)
    val id: Long = 0,
    @ColumnInfo(name = AMOUNT)
    val amount: String,
    @ColumnInfo(name = PAYMENT_METHOD)
    val paymentMethod: String,
    @ColumnInfo(name = ISSUER_ID)
    val bankId: String,
    @ColumnInfo(name = RECOMMENDED_MESSAGE)
    val recommendedMessage: String
)
