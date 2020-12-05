package com.example.pagingworkshop.training

import kotlinx.coroutines.*

fun main() {
    exampleRunBlocking()
    exampleRunBlockingInBetterWay()
    exampleRunBlockingBestApproach()
    exampleForDiff()
}

fun exampleRunBlocking() = runBlocking {//Blocking coroutine
    GlobalScope.launch {
        delay(1000) // Non Blocking
        println("World!")
    }
    println("Hello")
    delay(2000) // Not a best approach
}

fun exampleRunBlockingInBetterWay() = runBlocking {
    val job = GlobalScope.launch {
        delay(1000)
        println("John")
    }
    println("Hello")
    job.join()
}

//Structure Concurrency -launching coroutine in specific scope instead of the Global Scope
fun exampleRunBlockingBestApproach() = runBlocking {
    launch {
        delay(2000)
        println("Bob")
    }
    println("Hello")
}

/**
 * Notes:
 * runBlocking is a regular function (Blocks the main thread)
 * CoroutineScope is suspend function (Just suspends the main thread and is given free for other operations)
 * Check the example below
 */
fun exampleForDiff() = runBlocking {
    launch {
        delay(300)
        println("print from run block")
    }
    coroutineScope {
        launch {
            delay(500)
            println("Print from coroutine scope launch")
        }
        delay(100)
        println("print from coroutine scope")
    }
    println("Coroutine Scope is over")
}


