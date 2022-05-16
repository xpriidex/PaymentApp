package com.xpridex.paymentapp.presentation.bankselector.mapper

import com.xpridex.paymentapp.data.remote.model.BankApiModel
import com.xpridex.paymentapp.presentation.bankselector.model.Bank
import javax.inject.Inject

class BankMapper @Inject constructor() {

    fun List<BankApiModel>.toBank() = map { bankApiModel ->
        bankApiModel.toBank()
    }

    private fun BankApiModel.toBank() = Bank(
        id = id.orEmpty(),
        name = name.orEmpty(),
        urlImage = urlImage.orEmpty()
    )
}
