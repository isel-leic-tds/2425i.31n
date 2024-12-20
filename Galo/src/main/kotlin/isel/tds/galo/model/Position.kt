package isel.tds.galo.model

@JvmInline
value class Position private constructor( val index:Int){
    val row get() = index / BOARD_SIZE
    val col get() = index % BOARD_SIZE

    val backSlash get() = row == col
    val slash get() = row + col == BOARD_SIZE - 1

    override fun toString() = "$index"

    companion object{
        val values = List(BOARD_CELLS){Position(it)}

        operator fun invoke(index: Int): Position = values[index]
    }
}

fun Position(row: Int, col: Int): Position {
    require(row in 0 ..< BOARD_SIZE && col in 0 ..< BOARD_SIZE)
    return Position.values[row * BOARD_SIZE + col]
}

fun Int.toPositionOrNull(): Position? =
    if (this in Position.values.indices) Position(this) else null