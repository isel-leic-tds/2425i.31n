package org.example.isel.tds

class ImmutableStack<T> private constructor (private val head: Node<T>?){
    private class Node<E>(val elem: E, val next: Node<E>?)

    constructor():this(null)

    private fun topNodeOrException() = head ?: throw NoSuchElementException("Empty Stack")

    val top: T get() = (topNodeOrException()).elem

    fun isEmpty(): Boolean = head == null

    fun pop() = ImmutableStack(topNodeOrException().next)
    fun pop2() = top to pop()

    fun push(elem: T) = ImmutableStack( Node(elem, head))

    fun forEach(action: (T) -> Unit) {
        var node = head
        while (node != null) {
            action(node.elem)
            node = node.next
        }
    }

}