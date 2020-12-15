package com.example.pagingworkshop.training

import kotlinx.coroutines.*

var acquire = 0

class Resource {
    init {
        acquire++
    }

    fun close() {
        acquire--
    }
}

fun main() {
    print("Coroutine timeout")
    //timeOutUsage()
    //timeOutOrNullUsage()
    timeOutAsyncExample()
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

//Bad practise : acquire will not always 0
fun timeOutAsyncExample() = runBlocking {
    repeat(100) {
        launch {
            val resource = withTimeout(60) {
                delay(50)
                Resource()
            }
            resource.close()
        }
    }
    // called when the timeout ends
    print("Acquire is $acquire")
}

//Best practise: acquire will always 0
fun timeOutAsyncBetterExample() = runBlocking {
    var resource: Resource? = null
    repeat(100) {
        launch {
            try {
                withTimeout(60) {
                    delay(50)
                    resource = Resource()
                }
            } finally {
                resource?.close()
            }
        }
    }
    print("Acquire is $acquire")
}
