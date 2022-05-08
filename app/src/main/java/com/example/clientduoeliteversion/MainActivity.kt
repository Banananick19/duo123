package com.example.clientduoeliteversion

import android.os.Bundle
import android.service.controls.Control
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import banana.duo.duoKotlin.viewmodels.ClientViewModel
import com.example.clientduoeliteversion.Screens.Controller
import com.example.clientduoeliteversion.Screens.MainScreen
import com.example.clientduoeliteversion.Screens.WiFiConnectScreen
import com.example.clientduoeliteversion.ui.theme.ClientDuoEliteVersionTheme

class MainActivity : ComponentActivity() {
    val viewModel: ClientViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val state = remember { mutableStateOf(viewModel.clientState.value) }
            val navController = rememberNavController()
            ClientDuoEliteVersionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    NavHost(navController = navController, startDestination = "main") {
                        composable("main") {
                            MainScreen(viewModel, navController)
                        }
                        composable("WiFiConnect") {
                            WiFiConnectScreen(viewModel, navController)
                        }
                        composable("controller") {
                            Controller(navController, viewModel)
                        }
                    }
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ClientDuoEliteVersionTheme {
    }
}