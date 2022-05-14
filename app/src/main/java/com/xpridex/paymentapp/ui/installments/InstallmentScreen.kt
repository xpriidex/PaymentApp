package com.xpridex.paymentapp.ui.installments

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.xpridex.paymentapp.R
import com.xpridex.paymentapp.presentation.installmentselector.InstallmentSelectorUiState
import com.xpridex.paymentapp.ui.component.Loading
import com.xpridex.paymentapp.ui.component.PaymentsTopBar
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@FlowPreview
@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@Composable
internal fun InstallmentSelectorScreen(
    onBackEvent: () -> Unit,
    selectInstallmentEvent: (String) -> Unit,
    uiState: State<InstallmentSelectorUiState>
) {
    Scaffold(
        topBar = {
            PaymentsTopBar(
                title = stringResource(id = R.string.select_installment),
                onBackPress = onBackEvent
            )
        },
        content = {
            InstallmentSelectorContent(
                selectInstallmentEvent = selectInstallmentEvent,
                uiState = uiState.value
            )
        })
}

@ExperimentalMaterialApi
@Composable
private fun InstallmentSelectorContent(
    selectInstallmentEvent: (String) -> Unit,
    uiState: InstallmentSelectorUiState
) {
    if (uiState.isLoading) {
        Loading()
    } else {
        InstallmentComponent(
            selectInstallmentEvent = selectInstallmentEvent,
            recommendedMessages = uiState.recommendedMessages
        )
    }
}

@ExperimentalMaterialApi
@Composable
private fun InstallmentComponent(
    selectInstallmentEvent: (String) -> Unit,
    recommendedMessages: List<String>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(recommendedMessages) { recommendedMessage ->
            InstallmentCard(
                selectInstallmentEvent = selectInstallmentEvent,
                recommendedMessage = recommendedMessage
            )
        }

        item {
            Spacer(modifier = Modifier.size(10.dp))
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun InstallmentCard(
    selectInstallmentEvent: (String) -> Unit,
    recommendedMessage: String
) {
    Card(
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp, top = 10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 5.dp,
        backgroundColor = MaterialTheme.colors.surface,
        onClick = { selectInstallmentEvent.invoke(recommendedMessage) },
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Column(Modifier.padding(8.dp)) {
                Text(
                    text = recommendedMessage,
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

