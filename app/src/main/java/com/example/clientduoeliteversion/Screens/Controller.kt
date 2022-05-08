package com.example.clientduoeliteversion.Screens

import android.view.MotionEvent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.lifecycle.viewmodel.compose.viewModel
import banana.duo.duoKotlin.viewmodels.ClientViewModel
import com.example.client.ActionType
import com.example.client.Message
import com.example.clientduoeliteversion.viewmodels.ClientState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


@Composable
fun Controller(navController: NavController, viewModel: ClientViewModel) {
    val dx = remember { mutableStateOf(0f) }
    val dy = remember { mutableStateOf(0f) }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TouchPad(viewModel)
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun TouchPad(viewModel: ClientViewModel) {
    // actual composable state
    var offsetY = remember { mutableStateOf(0f) }
    var offsetX = remember { mutableStateOf(0f) }
    var lasty = remember { mutableStateOf(0f) }
    var lastx = remember { mutableStateOf(0f) }
    Box(
        modifier = Modifier
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        lastx.value = it.x
                        lasty.value = it.y
                        true
                    }
                    MotionEvent.ACTION_MOVE -> {
                        offsetX.value = it.x - lastx.value
                        offsetY.value = it.y - lasty.value
                        lastx.value = it.x
                        lasty.value = it.y
                        when (viewModel.clientState.value) {
                            is ClientState.ClientBluetoothConnecting -> TODO()
                            is ClientState.ClientConnected ->
                                runBlocking {
                                    launch(Dispatchers.IO) {
                                        val message = Message(ActionType.MouseMove, mapOf(Pair("x", offsetX.value.toInt().toString()), Pair("y", offsetY.value.toInt().toString())))
                                        (viewModel.clientState.value as ClientState.ClientConnected).client.sendMessage(
                                            message)
                                    }.join()
                                }
                            is ClientState.ClientWiFiConnecting -> TODO()
                            is ClientState.DefaultClientState -> TODO()
                            is ClientState.ErrorClientState<*> -> TODO()
                            null -> TODO()
                        }
                        true
                    }
                    MotionEvent.ACTION_UP -> {
                        true
                    }
                    else -> false
                }
            }
            .fillMaxSize()
    ) {
        Column{
            Text(offsetY.value.toString())
            Text(offsetX.value.toString())
        }

    }
}