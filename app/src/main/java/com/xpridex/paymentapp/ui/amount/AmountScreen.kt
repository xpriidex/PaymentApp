package com.xpridex.paymentapp.ui.amount

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.xpridex.paymentapp.R
import com.xpridex.paymentapp.presentation.amount.AmountUiState
import com.xpridex.paymentapp.ui.component.PaymentsTopBar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@Composable
internal fun AmountScreen(
    onBack: () -> Unit = {},
    onContinueEvent: (String) -> Unit,
    uiState: State<AmountUiState>
) {

    Scaffold(
        topBar = {
            PaymentsTopBar(
                title = stringResource(id = R.string.how_much_do_you_want_to_pay),
                onBackPress = onBack
            )
        },
        content = {
            AmountContent(uiState = uiState.value, onContinueEvent = onContinueEvent)
        })
}

@Composable
private fun AmountContent(
    onContinueEvent: (String) -> Unit,
    uiState: AmountUiState
) {
    var amount by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            modifier = Modifier
                .padding(top = 16.dp)
                .fillMaxWidth(),
            value = amount,
            onValueChange = {
                amount = it
            },
            isError = uiState.isEmpty,
            label = {
                val label = getTextFieldLabel(uiState.isEmpty)
                Text(label)
            },
            maxLines = 1,
            placeholder = { Text(stringResource(id = R.string.amount_to_pay)) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = { onContinueEvent.invoke(amount) },
            modifier = Modifier
                .padding(bottom = 16.dp)
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        ) {
            Text(stringResource(id = R.string.continue_))
        }
    }
}

@Composable
private fun getTextFieldLabel(isEmpty: Boolean): String {
    val label = if (isEmpty) {
        stringResource(id = R.string.you_must_add_an_amount)
    } else {
        stringResource(id = R.string.enter_the_amount_to_pay)
    }
    return label
}

