package com.xpridex.paymentapp.data.remote.model

import com.google.gson.annotations.SerializedName
import com.xpridex.paymentapp.data.remote.model.Constants.PAYER_COST

data class InstallmentsApiModel(
    @SerializedName(PAYER_COST) val payerCosts: List<PayerCostApiModel>?
)