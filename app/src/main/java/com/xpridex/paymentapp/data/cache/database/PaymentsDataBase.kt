package com.xpridex.paymentapp.data.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xpridex.paymentapp.data.cache.database.dao.PaymentsDao
import com.xpridex.paymentapp.data.cache.database.model.PaymentEntity

@Database(
    entities = [
        PaymentEntity::class
    ],
    version = 1
)
abstract class PaymentsDataBase : RoomDatabase() {
    abstract fun paymentsDao(): PaymentsDao
}