package org.example.isel.tds

interface Stack<T> : Iterable<T> {
    fun push(elem: T): Stack<T>
    fun pop(): Stack<T>
    val top: T
    fun isEmpty(): Boolean
}

fun <T> Stack(): Stack<T> = StackEmpty as Stack<T>


private class Node<T>(val elem: T, val next: Node<T>?)

private object StackEmpty : Stack<Any> {

    override fun push(elem: Any) = StackNotEmpty(Node(elem, null))
    override val top: Nothing get() = throwEmpty()
    override fun isEmpty() = true
    override fun pop() = throwEmpty()

    private fun throwEmpty(): Nothing {
        throw NoSuchElementException("stack empty")
    }
    override fun iterator() = object : Iterator<Any> {
        override fun hasNext() = false
        override fun next() = throwEmpty()
    }
}

private class StackNotEmpty<T>
    constructor(private val head: Node<T>) : Stack<T>{

    override fun push(elem: T): Stack<T> = StackNotEmpty(Node(elem, head))

    override fun pop(): Stack<T> = head.next?.let { StackNotEmpty(it) } ?: StackEmpty as Stack<T>
//    {
//        val next = head.next
//        return if (next != null) StackNotEmpty(next) else StackEmpty()
//    }

    override val top: T get() = head.elem

    override fun isEmpty(): Boolean = false

    override fun iterator(): Iterator<T> = object : Iterator<T> {
        var node: Node<T>? = head
        override fun hasNext() = node != null
        override fun next():T = (node ?: throw NoSuchElementException("no more elements"))
            .also { node = it.next }.elem
//        {
//            var n = node ?: throw NoSuchElementException()
//            node = n.next
//            return n.elem
//        }
    }

}