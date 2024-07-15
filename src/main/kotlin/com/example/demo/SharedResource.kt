package com.example.demo

import java.util.UUID

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

    fun generateValue(key: String) =
        synchronized(this) {
            val generatedValue = UUID.randomUUID().toString()
            mutableMap[key] = generatedValue
            println("generateValue: key [$key] for value [$generatedValue]")
        }
}