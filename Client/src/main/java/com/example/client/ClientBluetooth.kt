package com.example.client

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothSocket
import java.util.*

object ClientBluetooth : Client<ConnectionBluetooth> {
    lateinit var bluetoothSocket: BluetoothSocket

    override fun sendMessage(message: Message) {
        bluetoothSocket.outputStream.write(message.toString().toByteArray())
    }

    @SuppressLint("MissingPermission")
    override fun connect(connectionObject: ConnectionBluetooth) {
        connectionObject.apply {
            val device = bluetoothAdapter.getRemoteDevice(address)
            bluetoothSocket = device.createInsecureRfcommSocketToServiceRecord(UUID.randomUUID())
            bluetoothAdapter.cancelDiscovery()
            bluetoothSocket.connect()
        }
    }

    override fun isConnect(): Boolean = bluetoothSocket.isConnected

}