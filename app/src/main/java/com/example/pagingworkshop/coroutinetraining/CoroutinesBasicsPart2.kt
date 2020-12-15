package com.example.pagingworkshop.coroutinetraining

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() {
    // manyRunBlockRequests()
    //manyScopeRequest()
    callToSuspendFunction()
}

/**
 * Will keep the process alive until finishes
 */
fun manyRunBlockRequests() = runBlocking {
    repeat(100_000) {
        delay(1000)
        print("I am runBlocking")
    }
}

/**
 * Will ends when the scope ends and do not like the process alive;
 * Eg they are lke daemon threads
 */
fun manyScopeRequest() = runBlocking {
    GlobalScope.launch {
        repeat(100) {
            delay(3000)
            print("I am in scope")
        }
    }
    delay(10000)
}

fun callToSuspendFunction() = runBlocking {
    launch {
        doSomething()
    }
}

/**
 *Suspend function
 */

suspend fun doSomething() {
    delay(1000)
    println("Print from suspend function")
}
