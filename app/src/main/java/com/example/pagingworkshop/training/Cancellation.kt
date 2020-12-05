package com.example.pagingworkshop.training

import kotlinx.coroutines.*

fun main() {
//    cancelTheCoroutine()
//    unCancelableCoroutine()
//    cancelableCoroutine()
//    finallyExample()
    finallyExampleSecond()
}

fun cancelTheCoroutine() = runBlocking {
    val job = launch {
        repeat(1000) { i ->
            delay(3000)
            println("I am waiting since $i second")
        }
    }
    delay(10000)
    println("I am tired of waiting")
    job.cancelAndJoin()
    println("I am quiting waiting")
}

/**
 * Without the usage of isActive to check if the job is cancelled
 */
fun unCancelableCoroutine() = runBlocking {
    val job = launch {
        var i = 0
        while (i < 5) {
            delay(3000)
            print("I am running $i times")
            i++
        }
    }
    delay(10000)
    println("I am tired of running")
    job.cancelAndJoin()
    println("I am quiting running")
}

/**
 * Use of isActive to check if the job is cancelled
 */
fun cancelableCoroutine() = runBlocking {
    val job = launch {
        var i = 0
        while (i < 5) {
            delay(3000)
            print("I am walking $i times")
            i++
        }
    }
    delay(10000)
    println("I am tired of walking")
    job.cancelAndJoin()
    println("I am quiting walking")
}

/**
 * Use of try and finally
 */

fun finallyExample() = runBlocking {
    val job = launch {
        try {
            repeat(100) {
                delay(2000)
                println("finally example")
            }
        } finally { //Suspend functions cannot be used in finally block
            println("Finally block called")
            delay(1000) // will not called, throws exception
            println("Delayed for one second") // will not called
        }
    }

    delay(10000)
    println("Going to cancel")
    job.cancelAndJoin()
    println("Cancelled and Joined")
}

fun finallyExampleSecond() = runBlocking {
    val job = launch {
        try {
            repeat(100) {
                delay(2000)
                println("finally example")
            }
        } finally { //Suspend functions cannot be used in finally block
            withContext(NonCancellable) {
                println("Finally block called")
                delay(1000) // will be called
                println("Delayed for one second") // will be called
            }
        }
    }
    delay(10000)
    println("Going to cancel")
    job.cancelAndJoin()
    println("Cancelled and Joined")
}
