package com.example.demo

import kotlinx.coroutines.delay
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.UUID

class SharedResource {

    private val mutex: Mutex = Mutex()
    private var mutableMap: MutableMap<String, String> = mutableMapOf<String, String>()

    suspend fun get(key: String): String? {
        mutex.withLock {
            val value = mutableMap[key]
            println("get: key [$key] for value [$value]")
            return value
        }
    }

    suspend fun put(key: String, value: String) {
        mutex.withLock {
            mutableMap[key] = value
            println("put: key [$key] for value [$value]")
        }
    }

    suspend fun generateValue(key: String) {
        println("generateValue: generating value...")
        delay(1000L)
        val generatedValue = UUID.randomUUID().toString()
        println("generateValue: value generated [$generatedValue]")

        mutex.withLock {
            put(key, generatedValue)
        }
    }
}