package com.example.client

import com.google.gson.Gson

class Message(val actionType: ActionType, val content: Map<String, String>) {

    override fun toString(): String {
        return gson.toJson(this)
    }

    companion object {
        var gson: Gson = Gson()
        fun parseString(string: String?): Message {
            return gson.fromJson(string, Message::class.java)
        }
    }

}