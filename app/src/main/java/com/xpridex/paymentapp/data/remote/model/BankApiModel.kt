package com.xpridex.paymentapp.data.remote.model

import com.google.gson.annotations.SerializedName
import com.xpridex.paymentapp.data.remote.model.Constants.ID
import com.xpridex.paymentapp.data.remote.model.Constants.NAME
import com.xpridex.paymentapp.data.remote.model.Constants.TUMBNAIL

data class BankApiModel(
    @SerializedName(ID) val id: String?,
    @SerializedName(NAME) val name: String?,
    @SerializedName(TUMBNAIL) val urlImage: String?
)