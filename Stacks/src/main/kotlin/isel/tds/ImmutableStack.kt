package org.example.isel.tds

class ImmutableStack<T> private constructor(private val head: Node<T>?) : Iterable<T> {
    private class Node<E>(val elem: E, val next: Node<E>?)

    constructor() : this(null)

    private fun topNodeOrException() = head ?: throw NoSuchElementException("Empty Stack")

    val top: T get() = (topNodeOrException()).elem

    fun isEmpty(): Boolean = head == null

    fun pop() = ImmutableStack(topNodeOrException().next)
    fun pop2() = top to pop()

    fun push(elem: T) = ImmutableStack(Node(elem, head))

    fun forEach(action: (T) -> Unit) {
        var node = head
        while (node != null) {
            action(node.elem)
            node = node.next
        }
    }

    //1st Version using Nested Class without access to the outer class state
//    private class It<E>(private var node :Node<E>?):Iterator<E>{
//        override fun hasNext() = node != null
//        override fun next() :E = (node ?: throw NoSuchElementException()).also { node = it.next }.elem
//    }
//    override fun iterator(): Iterator<T> = It<T>(head)
//2nd version using inner classes that can access state on the outer class

    //3rd version using implicit inner class
//    override fun iterator(): Iterator<T> {
//        class It():Iterator<T>{
//            private var node :Node<T>? = head
//            override fun hasNext() = node != null
//            override fun next() :T = (node ?: throw NoSuchElementException()).also { node = it.next }.elem
//        }
//        return It()
//    }
//4th version using anonymous object
    override fun iterator(): Iterator<T> = object : Iterator<T> {
        private var node: Node<T>? = head
        override fun hasNext() = node != null
        override fun next(): T = (node ?: throw NoSuchElementException()).also { node = it.next }.elem
    }

    override fun equals(other: Any?): Boolean {
//        if (other !is ImmutableStack<*>) return false
//        var n1 = head
//        var n2 = other.head
//        while (n1 != null && n2 != null) {
//            if (n1.elem != n2.elem) return false
//            n1 = n1.next
//            n2 = n2.next
//        }
//        return n1 == null && n2 == null
        if (other !is ImmutableStack<*>) return false
        var n = other.head
        for (elem in this) {
            if (n == null || elem != n.elem) return false
            n = n.next
        }
        return n == null
    }

    //    override fun hashCode(): Int {
//        var n = head
//        var hash = 0
//        while (n != null) {
//            hash = 31 * hash + n.elem.hashCode()
//            n = n.next
//        }
//        return hash
//    }
    override fun hashCode() =
        this.fold(0) { acc, elem -> 31 * acc + elem.hashCode() }
}
