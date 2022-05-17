package com.xpridex.paymentapp.data.remote.model

import com.google.gson.annotations.SerializedName
import com.xpridex.paymentapp.data.remote.model.Constants.RECOMMENDED_MESSAGE

data class PayerCostApiModel(
    @SerializedName(RECOMMENDED_MESSAGE) val recommendedMessage: String?
)