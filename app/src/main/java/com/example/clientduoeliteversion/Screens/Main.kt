package com.example.clientduoeliteversion.Screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import banana.duo.duoKotlin.extensions.set
import banana.duo.duoKotlin.viewmodels.ClientViewModel
import com.example.clientduoeliteversion.viewmodels.ClientState


@Composable
fun MainScreen(viewModel: ClientViewModel, navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(modifier = Modifier.padding(40.dp, 40.dp), horizontalArrangement = Arrangement.Center) {
            Button({
                viewModel.clientState.set(ClientState.ClientWiFiConnecting())
                navController.navigate(route = "WiFiConnect")
            }) {
                Text("WI-FI")
            }
        }

        Row(modifier = Modifier.padding(40.dp, 40.dp), horizontalArrangement = Arrangement.Center) {
            Button({ }) {
                Text("Bluetooth")
            }
        }
    }
}