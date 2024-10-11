package isel.tds.galo.model

class Board(
    val cells: List<Char> =
//        List(9) { ' ' },
        listOf(
            ' ', ' ', ' ',
            ' ', ' ', ' ',
            ' ', ' ', ' '
        ),
    val turn: Char = 'X' // X or O
)


fun Board.play(pos: Int): Board = Board(
    cells = cells.mapIndexed { idx, c -> if (idx == pos) turn else c },
    turn = if (turn == 'X') 'O' else 'X'
)

fun Board.freeCell(pos: Int): Boolean {
    return cells[pos] == ' '
}