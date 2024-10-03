package org.example.isel.tds

//1st version
class MutableStackImmutableList<T> {

    private var elems = emptyList<T>()

    val top: T get() = elems.last()
    fun isEmpty(): Boolean = elems.isEmpty()
    fun pop():T {
        var elem = elems.last()
        elems = elems.dropLast(1)
        return elem
    }
    fun push(elem: T) {
        elems = elems + elem
    }

    override fun equals(other: Any?): Boolean = other is MutableStackImmutableList<*> && elems == other.elems;
    override fun hashCode(): Int = elems.hashCode()
}

//2nd version
class MutableStackWithMutableList<T> {

    private val elems = mutableListOf<T>()

    val top: T get() = elems.last()
    fun isEmpty(): Boolean = elems.isEmpty()
    fun pop():T = elems.removeLast()

    fun push(elem: T) {
        elems.add(elem)
    }

    override fun equals(other: Any?): Boolean = other is MutableStackWithMutableList<*> && elems == other.elems;
    override fun hashCode(): Int = elems.hashCode()
}

//3rd version
class MutableStack<T> {
    private class Node<E>(val elem: E, val next: Node<E>?)
    private var head: Node<T>? = null

    val top: T get() = (topNodeOrException()).elem

    private fun topNodeOrException() = head ?: throw NoSuchElementException("Empty Stack")
    fun isEmpty(): Boolean = head == null
    fun pop():T = top.also { head = head?.next }
//    fun pop():T = topNodeOrException().also { head = it.next }.elem
//    fun pop():T {
//        val node = topNodeOrException()
//        head = node.next
//        return node.elem
//    }
    fun push(elem: T) {
        head = Node(elem, head)
    }

    override fun equals(other: Any?): Boolean {
        if (other !is MutableStack<*>) return false
        var n1 = head
        var n2 = other.head
        while (n1 != null && n2 != null) {
            if (n1.elem != n2.elem) return false
            n1 = n1.next
            n2 = n2.next
        }
        return n1 == null && n2 == null
    }
    override fun hashCode(): Int {
        var n = head
        var hash = 0
        while (n != null) {
            hash = 31 * hash + n.elem.hashCode()
            n = n.next
        }
        return hash
    }
}