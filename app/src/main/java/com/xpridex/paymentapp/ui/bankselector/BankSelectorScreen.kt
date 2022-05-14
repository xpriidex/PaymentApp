package com.xpridex.paymentapp.ui.bankselector

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.xpridex.paymentapp.R
import com.xpridex.paymentapp.data.remote.model.BankApiModel
import com.xpridex.paymentapp.presentation.bankselector.BankSelectorUiState
import com.xpridex.paymentapp.ui.component.Loading
import com.xpridex.paymentapp.ui.component.PaymentsTopBar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
internal fun BankSelectorScreen(
    onBackEvent: () -> Unit,
    selectBankEvent: (String) -> Unit,
    uiState: State<BankSelectorUiState>
) {
    Scaffold(
        topBar = {
            PaymentsTopBar(
                title = stringResource(id = R.string.select_bank),
                onBackPress = onBackEvent
            )
        },
        content = {
            BankSelectorContent(
                selectBankEvent = selectBankEvent,
                uiState = uiState.value
            )
        })
}

@ExperimentalMaterialApi
@Composable
private fun BankSelectorContent(
    selectBankEvent: (String) -> Unit,
    uiState: BankSelectorUiState
) {
    if (uiState.isLoading) {
        Loading()
    } else {
        BanksComponent(
            selectBankEvent = selectBankEvent,
            banks = uiState.banks
        )
    }
}

@ExperimentalMaterialApi
@Composable
private fun BanksComponent(
    selectBankEvent: (String) -> Unit,
    banks: List<BankApiModel>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(banks) { bank ->
            BankCard(
                selectBankEvent = selectBankEvent,
                bank = bank
            )
        }

        item {
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun BankCard(
    selectBankEvent: (String) -> Unit,
    bank: BankApiModel
) {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface,
        onClick = { selectBankEvent.invoke(bank.id.orEmpty()) },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Image(
                painter = rememberAsyncImagePainter( bank.urlImage,),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit,
            )

            Column(Modifier.padding(8.dp)) {
                Text(
                    text = bank.name.orEmpty(),
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onSurface,
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Icon(
                modifier = Modifier.padding(end = 10.dp),
                imageVector = Icons.Filled.ChevronRight,
                contentDescription = null
            )
        }
    }
}
