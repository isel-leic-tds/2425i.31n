package isel.tds.galo.model

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class BoardTest{
    @Test
    fun testFreeCell(){
        val sut = Board(
            cells = listOf(
                Player.O, Player.X, Player.O,
                Player.X, Player.X, null,
                Player.X, Player.O, Player.X
            ),
            turn = Player.O
        )
//        assertFalse( sut.freeCell(0))
//        assertTrue( sut.freeCell(5))
    }
    @Test
    fun testPlay(){
        val sut = Board(
            cells = listOf(
                Player.O, Player.X, Player.O,
                Player.X, Player.X, null,
                Player.X, Player.O, Player.X
            ),
            turn = Player.O
        )
        val newBoard = sut.play(Position(1,2))
        assertEquals(Player.O, newBoard.cells[5])
        assertEquals(Player.X, newBoard.turn)
    }
}