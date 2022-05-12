package com.xpridex.paymentapp.data.remote.model

import com.google.gson.annotations.SerializedName
import com.xpridex.paymentapp.data.remote.model.Constants.ID
import com.xpridex.paymentapp.data.remote.model.Constants.NAME
import com.xpridex.paymentapp.data.remote.model.Constants.PAYMENT_TYPE_ID

data class PaymentMethodApiModel(
    @SerializedName(ID) val id: String?,
    @SerializedName(NAME) val name: String?,
    @SerializedName(PAYMENT_TYPE_ID) val paymentTypeId: String?
)