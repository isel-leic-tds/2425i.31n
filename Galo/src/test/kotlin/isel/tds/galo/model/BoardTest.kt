package isel.tds.galo.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BoardTest{
    @Test
    fun testFreeCell(){
        val sut = Board(
            cells = listOf(
                'O', 'X', 'O',
                'X', 'X', ' ',
                'X', 'O', 'X'
            ),
            turn = 'O'
        )
        assertFalse( sut.freeCell(0))
        assertTrue( sut.freeCell(5))
    }
    @Test
    fun testPlay(){
        val sut = Board(
            cells = listOf(
                'O', 'X', 'O',
                'X', 'X', ' ',
                'X', 'O', 'X'
            ),
            turn = 'O'
        )
        val newBoard = sut.play(5)
        assertEquals('O', newBoard.cells[5])
        assertEquals('X', newBoard.turn)
    }
}