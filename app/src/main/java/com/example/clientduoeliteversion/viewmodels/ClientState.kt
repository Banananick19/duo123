package com.example.clientduoeliteversion.viewmodels

import com.example.client.Client
import com.example.client.ConnectionDescription

sealed class ClientState {
    class ClientConnected(val client: Client<ConnectionDescription>): ClientState()
    class DefaultClientState: ClientState()
    class ErrorClientState<T>(val errorMessage: T): ClientState()
    class ClientWiFiConnecting: ClientState()
    class ClientBluetoothConnecting: ClientState()
}