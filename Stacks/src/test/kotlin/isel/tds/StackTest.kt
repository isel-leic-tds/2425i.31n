package isel.tds

import org.example.isel.tds.Stack
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class StackTest{

    @Test
    fun emptyStackConditions() {
        val stk = Stack<String>()
        assertTrue(stk.isEmpty())
        assertFailsWith<NoSuchElementException> { stk.top }
        assertFailsWith<NoSuchElementException> { stk.pop() }
    }
    @Test
    fun notEmptyCondition(){
        val sut = Stack<String>()
        sut.push("A")
        assertFalse(sut.isEmpty())
        assertEquals("A", sut.top)
        assertEquals("A", sut.pop())
        assertTrue(sut.isEmpty())
    }
    @Test
    fun stackWithTwoElements(){
        val sut = Stack<String>()
        sut.push("A")
        sut.push("B")
        assertEquals("B", sut.top)
        assertEquals("B", sut.pop())
        assertEquals("A", sut.top)
        assertEquals("A", sut.pop())
        assertTrue(sut.isEmpty())
    }

    @Test
    fun testEqualityOfStacks(){
        val sut = Stack<Char>()
        sut.push('A')
        sut.push('B')
        val sut2 = Stack<Char>()
        sut2.push('A')
        sut2.push('B')
        assertEquals(sut, sut2)
        assertEquals(sut.hashCode(), sut2.hashCode())
        sut2.pop()
        assertNotEquals(sut, sut2)
        assertNotEquals(sut.hashCode(), sut2.hashCode())
    }
}