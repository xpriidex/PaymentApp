package com.xpridex.paymentapp.ui.paymentmethod

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.xpridex.paymentapp.R
import com.xpridex.paymentapp.domain.model.PaymentMethod
import com.xpridex.paymentapp.presentation.paymentmethod.PaymentMethodUiState
import com.xpridex.paymentapp.ui.component.Loading
import com.xpridex.paymentapp.ui.component.PaymentsTopBar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
internal fun PaymentMethodSelectorScreen(
    onBackEvent: () -> Unit,
    selectPaymentEvent: (String) -> Unit,
    uiState: State<PaymentMethodUiState>
) {
    Scaffold(
        topBar = {
            PaymentsTopBar(
                title = stringResource(id = R.string.select_payment_method),
                onBackPress = onBackEvent
            )
        },
        content = {
            PaymentMethodContent(
                selectPaymentEvent = selectPaymentEvent,
                uiState = uiState.value
            )
        })
}

@ExperimentalMaterialApi
@Composable
private fun PaymentMethodContent(
    selectPaymentEvent: (String) -> Unit,
    uiState: PaymentMethodUiState
) {
    if (uiState.isLoading) {
        Loading()
    } else {
        PaymentMethods(
            selectPaymentEvent = selectPaymentEvent,
            paymentMethods = uiState.paymentMethods
        )
    }
}

@ExperimentalMaterialApi
@Composable
private fun PaymentMethods(
    selectPaymentEvent: (String) -> Unit,
    paymentMethods: List<PaymentMethod>
) {
    val paymentMethodsRemember = remember { mutableStateOf(paymentMethods) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(paymentMethodsRemember.value) { paymentMethod ->
            PaymentMethodCard(
                selectPaymentEvent = selectPaymentEvent,
                paymentMethod = paymentMethod
            )
        }

        item {
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun PaymentMethodCard(
    selectPaymentEvent: (String) -> Unit,
    paymentMethod: PaymentMethod
) {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface,
        onClick = { selectPaymentEvent.invoke(paymentMethod.id) },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Image(
                painter = rememberAsyncImagePainter(paymentMethod.urlImage),
                contentDescription = null,
                modifier = Modifier
                    .size(80.dp)
                    .padding(8.dp),
                contentScale = ContentScale.Fit,
            )

            Column(Modifier.padding(8.dp)) {
                Text(
                    text = paymentMethod.name,
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
