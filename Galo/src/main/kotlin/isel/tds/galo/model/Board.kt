package isel.tds.galo.model

const val BOARD_SIZE = 3
const val BOARD_CELLS = BOARD_SIZE * BOARD_SIZE

class Board(
    val cells: List<Player?> =
        List(BOARD_CELLS) { null },
//        listOf(
//            ' ', ' ', ' ',
//            ' ', ' ', ' ',
//            ' ', ' ', ' '
//        ),
    val turn: Player = Player.X // X or O
)


fun Board.play(pos: Position): Board {
    check(cells[pos.index] == null) { "Position $pos is already used" }

    return Board(
        cells = cells.mapIndexed { idx, cellContent -> if (idx == pos.index) turn else cellContent },
        turn = turn.other
    )
}

//fun Board.freeCell(pos: Int): Boolean {
//    return cells[pos] == null
//}

fun Board.isWinner(player: Player) =
    (0..2).any { col -> (0..6 step 3).all { cells[col + it] == player } } ||
        (0..6 step 3).any { row -> (0..2).all { cells[row + it] == player } } ||
        (0..8 step 4).all { cells[it] == player } ||
        (2..6 step 2).all { cells[it] == player }









