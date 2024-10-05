package isel.tds

import org.example.isel.tds.ImmutableStack
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class ImmutableStackTest{
    @Test
    fun emptyStackConditions() {
        val stk = ImmutableStack<String>()
        assertTrue(stk.isEmpty())
        assertFailsWith<NoSuchElementException> { stk.top }
        assertFailsWith<NoSuchElementException> { stk.pop() }
    }

    @Test
    fun notEmptyCondition(){
        val emptyStack = ImmutableStack<String>()
        val oneElemStack = emptyStack.push("A")
        assertTrue(emptyStack.isEmpty())
        assertFalse(oneElemStack.isEmpty())
        assertEquals("A", oneElemStack.top)
        val finalEmptyStackPair = oneElemStack.pop2()
        assertEquals("A", finalEmptyStackPair.first)
        assertTrue(finalEmptyStackPair.second.isEmpty())
    }

    @Test fun `stack operations`() {
        val threeElemStack = ImmutableStack<Char>().push('A').push('B').push('C')
        assertEquals('C', threeElemStack.top)
        assertEquals('B', threeElemStack.pop().top) // equivalent to the next lines of code
//        val twoElemStack = threeElemStack.pop()
//        assertEquals('B', twoElemStack.top)
        val oneElemStack = threeElemStack.pop().pop()
        assertEquals('A', oneElemStack.top)
    }

    @Test fun testForEachCustomFun(){
        val threeElemStack = ImmutableStack<Int>().push(1).push(2).push(3)
        var sum = 0
        threeElemStack.forEach { sum += it }
        println("sum = $sum, top = ${threeElemStack.top}") // Output: sum = 6, top = 3

        assertEquals(6, sum)
        assertEquals(3, threeElemStack.top)

    }

    @Test fun testForEach(){
        val stk = ImmutableStack<Int>().push(1).push(2).push(3)
        var sum = 0
        for(e in stk) {
            sum += e
        }
        println("sum = $sum, top = ${stk.top}") // Output: sum = 6, top = 3

        assertEquals(6, sum)
        assertEquals(3, stk.top)

        assertEquals(6, stk.sum())
    }


    @Test fun `equality of stacks`() {
        var sut = ImmutableStack<Char>()
        sut = sut.push('A').push('B')
        var sut2 = ImmutableStack<Char>()
        sut2 = sut2.push('A').push('B')
        assertEquals(sut, sut2)
        assertEquals(sut.hashCode(), sut2.hashCode())
        sut2 = sut2.pop()
        assertNotEquals(sut, sut2)
        assertNotEquals(sut.hashCode(), sut2.hashCode())

    }
}
