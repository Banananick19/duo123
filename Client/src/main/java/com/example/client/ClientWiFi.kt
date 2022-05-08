package com.example.client

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.Socket

object ClientWiFi : Client<ConnectionWiFi> {
    var clientSocket: Socket? = null
    lateinit var inBuffer: BufferedReader
    lateinit var outBuffer: BufferedWriter

    override fun sendMessage(message: Message) {
        if (clientSocket == null || !clientSocket!!.isConnected) return
        outBuffer.write(message.toString() + "\n")
        outBuffer.flush()
    }

    override fun connect(connectionObject: ConnectionWiFi) {

        clientSocket = Socket(connectionObject.ip, connectionObject.port)
        inBuffer = BufferedReader(
            InputStreamReader(
                clientSocket!!.getInputStream()
            )
        )
        outBuffer = BufferedWriter(
            OutputStreamWriter(
                clientSocket!!.getOutputStream()
            )
        )

    }

    override fun isConnect(): Boolean {
        if (clientSocket != null) return clientSocket!!.isConnected
        else return false
    }

}