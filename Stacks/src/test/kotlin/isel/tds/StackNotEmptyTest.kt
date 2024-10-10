package isel.tds

import org.example.isel.tds.ImmutableStack
import org.example.isel.tds.Stack
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StackNotEmptyTest{
    fun notEmptyCondition(){
        val emptyStack = Stack<String>()
        val oneElemStack = emptyStack.push("A")
        assertTrue(emptyStack.isEmpty())
        assertFalse(oneElemStack.isEmpty())
        assertEquals("A", oneElemStack.top)
        assertTrue(oneElemStack.pop().isEmpty())
    }

    @Test
    fun `stack operations`() {
        val threeElemStack = Stack<Char>().push('A').push('B').push('C')
        assertEquals('C', threeElemStack.top)
        assertEquals('B', threeElemStack.pop().top) // equivalent to the next lines of code

        val oneElemStack = threeElemStack.pop().pop()
        assertEquals('A', oneElemStack.top)
    }

}