package com.xpridex.paymentapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.xpridex.paymentapp.R
import com.xpridex.paymentapp.presentation.home.HomeUiState
import com.xpridex.paymentapp.presentation.home.model.Payment
import com.xpridex.paymentapp.ui.component.Loading
import com.xpridex.paymentapp.ui.component.PaymentsTopBar
import com.xpridex.paymentapp.ui.extension.swapList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
internal fun HomeScreen(
    onPaymentEvent: () -> Unit,
    uiState: State<HomeUiState>
) {
    Scaffold(
        topBar = {
            PaymentsTopBar(title = stringResource(id = R.string.Payments))
        },
        content = {
            HomeContent(
                onPaymentEvent = onPaymentEvent,
                uiState = uiState.value
            )
        })
}

@ExperimentalMaterialApi
@Composable
private fun HomeContent(
    onPaymentEvent: () -> Unit,
    uiState: HomeUiState
) {
    if (uiState.isLoading) {
        Loading()
    } else {
        HomeComponent(
            onPaymentEvent = onPaymentEvent,
            payments = uiState.payments
        )
    }
}

@ExperimentalMaterialApi
@Composable
private fun HomeComponent(
    onPaymentEvent: () -> Unit,
    payments: List<Payment>
) {

    val paymentList = remember { mutableStateListOf<Payment>() }
    paymentList.swapList(payments)

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 10.dp)
    ) {
        item {
            OutlinedButton(
                onClick = { onPaymentEvent.invoke() },
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            ) {
                Text("Realizar un pago")
            }
        }
        item {
            Text(
                text = "Pagos anteriores",
                style = MaterialTheme.typography.h6
            )
        }

        items(paymentList) { payment ->
            PaymentCard(
                payment = payment
            )
        }

        item {
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun PaymentCard(
    payment: Payment,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Monto Pagado:",
                style = MaterialTheme.typography.body1
            )

            Text(
                text = payment.amount,
                style = MaterialTheme.typography.body1
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Medio de pago:",
                style = MaterialTheme.typography.body1
            )

            Text(
                text = payment.paymentMethod,
                style = MaterialTheme.typography.body1
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Banco:",
                style = MaterialTheme.typography.body1
            )

            Text(
                text = payment.bankName,
                style = MaterialTheme.typography.body1
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Cuotas:",
                style = MaterialTheme.typography.body1
            )

            Text(
                text = payment.recommendedMessage,
                style = MaterialTheme.typography.body1
            )
        }

        Divider(
            color = Color.LightGray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp),
            thickness = 1.dp
        )
    }
}

