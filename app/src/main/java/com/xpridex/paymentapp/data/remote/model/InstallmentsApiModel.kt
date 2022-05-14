package com.xpridex.paymentapp.data.remote.model

import com.google.gson.annotations.SerializedName
import com.xpridex.paymentapp.data.remote.model.Constants.ID
import com.xpridex.paymentapp.data.remote.model.Constants.NAME
import com.xpridex.paymentapp.data.remote.model.Constants.PAYER_COST
import com.xpridex.paymentapp.data.remote.model.Constants.TUMBNAIL

data class InstallmentsApiModel(
    @SerializedName(PAYER_COST) val payerCosts: List<PayerCostApiModel>?
)