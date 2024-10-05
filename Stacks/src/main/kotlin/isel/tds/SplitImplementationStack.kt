package org.example.isel.tds

interface Stack<T> : Iterable<T> {
    fun push(value: T): Stack<T>
    fun pop(): Stack<T>
    val top: T
    fun isEmpty(): Boolean
}

fun x(){
    TODO()
}