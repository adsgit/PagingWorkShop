package com.example.pagingworkshop.training

import kotlinx.coroutines.*

fun main() {
    print("Coroutine timeout")
    timeOutUsage()
    timeOutOrNullUsage()
}

fun timeOutUsage() = runBlocking {
    try {
        withTimeout(10000) {
            repeat(10) {
                println("I am waiting")
                delay(100)
            }
        }
    } finally {
        withContext(NonCancellable) {
            println("Delaying")
            delay(1000)
            println("Cancelled")
        }
    }
}

fun timeOutOrNullUsage() = runBlocking {
    try {
        withTimeoutOrNull(10000) {
            repeat(100) {
                println("Time out or null")
                delay(500)
            }
        }
    } finally {
        withContext(NonCancellable) {
            println("cancelled")
        }
    }
}
