package com.example.demo

class SharedResource {

    private var mutableMap = mutableMapOf<String, String>()

    fun get(key: String): String? =
        synchronized(this) {
            val value = mutableMap[key]
            println("get: key [$key] got value [$value]")
            value
        }

    fun put(key: String, value: String) =
        synchronized(this) {
            mutableMap[key] = value
            println("put: key [$key] for value [$value]")
        }
}