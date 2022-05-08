package com.example.client

import android.bluetooth.BluetoothAdapter

interface ConnectionDescription {

}

class ConnectionWiFi(val ip: String, val port: Int) : ConnectionDescription {
}

class ConnectionBluetooth(val bluetoothAdapter: BluetoothAdapter, val address: String) : ConnectionDescription {
}

