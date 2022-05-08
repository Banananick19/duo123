package com.example.client

interface Client< T : ConnectionDescription> {
    fun sendMessage(message: Message)
    fun connect(connectionObject: T)
    fun isConnect(): Boolean
}