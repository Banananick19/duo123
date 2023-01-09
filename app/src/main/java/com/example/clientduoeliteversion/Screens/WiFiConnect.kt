package com.example.clientduoeliteversion.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import banana.duo.duoKotlin.extensions.set
import banana.duo.duoKotlin.viewmodels.ClientViewModel
import com.example.client.Client
import com.example.client.ClientWiFi
import com.example.client.ConnectionDescription
import com.example.client.ConnectionWiFi
import com.example.clientduoeliteversion.viewmodels.ClientState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.lang.Exception


@Composable
fun WiFiConnectScreen(viewModel: ClientViewModel, navController: NavController) {
    var ip by remember { mutableStateOf("") }
    var port by remember { mutableStateOf("") }

    fun onSubmit() {
        runBlocking {
            launch(Dispatchers.IO) {
                if (validIp(ip) || validPort(port)) {
                    try {
                        ClientWiFi.connect(ConnectionWiFi(ip, port.toInt()))
                    } catch (ex: Exception) {
                        //TODO:toast
                    }
                }
            }
        }
        viewModel.clientState.set(ClientState.ClientConnected(ClientWiFi as Client<ConnectionDescription>))
        navController.navigate(route = "controller")
    }
    if (ClientWiFi.isConnect()) {
        Row(modifier = Modifier.padding(40.dp, 40.dp), horizontalArrangement = Arrangement.Center) {
            Button({navController.navigate(route = "controller")}) {
                Text("Использовать существующее подключение")
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.padding(40.dp, 40.dp), horizontalArrangement = Arrangement.Center) {
            OutlinedTextField(
                value = ip,
                onValueChange = {
                    ip = it
                },
                label = { Text("ip") },
                trailingIcon = {
                    if (validIp(ip)) {
                        Icon(Icons.Filled.Check, contentDescription = "Проверено")
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        Row(modifier = Modifier.padding(40.dp, 40.dp), horizontalArrangement = Arrangement.Center) {
            OutlinedTextField(
                value = port,
                onValueChange = {
                    port = it
                },
                label = { Text("port") },
                trailingIcon = {
                    if (validPort(port)) {
                        Icon(Icons.Filled.Check, contentDescription = "проверено")
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
        }

        Row(
            modifier = Modifier.padding(40.dp, 40.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button({ onSubmit() }) {
                Text("Submit")
            }
        }
    }

}



fun validIp(ip: String): Boolean =
    ip.matches("^((25[0-5]|2[0-4]\\d|1\\d{2}|[1-9]?\\d)(\\.|\$)){4}\\b".toRegex())

fun validPort(port: String): Boolean {
    return try {
        Integer.parseInt(port) in 1..65535
    } catch (ex: Exception) {
        false
    }
}
