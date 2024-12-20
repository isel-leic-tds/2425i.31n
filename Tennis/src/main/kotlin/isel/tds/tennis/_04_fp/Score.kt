package isel.tds.isel.tds.tennis._04_fp

import isel.tds.isel.tds.tennis.Player
import isel.tds.isel.tds.tennis.Points
import isel.tds.isel.tds.tennis.Points.*


class Score(
    val placard: String,
    val isGame: Boolean = false,  // default parameter
    val next: (win: Player) -> Score
)

private fun game(winner: Player) = Score(
    placard = "Game $winner",
    isGame = true,
    next = { playerThatWonThePoint: Player -> error("Is over") }
)

private fun advantage(player: Player): Score = Score(
    placard = "Advantage $player",
    next = { playerThatWonThePoint -> if (playerThatWonThePoint == player) game(playerThatWonThePoint) else deuce() }
)

private fun deuce() = Score("Deuce", next = ::advantage)

private fun byPoints(pointsA: Points, pointsB: Points): Score = Score(
    placard = "${pointsA.value} - ${pointsB.value}",
    next = { playerThatWonThePoint ->

        when {
            (playerThatWonThePoint == Player.A && pointsA == FORTY)
                    || (playerThatWonThePoint == Player.B && pointsB == FORTY) -> game(playerThatWonThePoint)

            (playerThatWonThePoint == Player.B && pointsA == FORTY && pointsB == THIRTY)
                    || (playerThatWonThePoint == Player.A && pointsB == FORTY && pointsA == THIRTY) -> deuce()

            playerThatWonThePoint == Player.A -> byPoints(pointsA.next(), pointsB)
            else -> byPoints(pointsA, pointsB.next())
        }
    }
)

fun initialScore(): Score = byPoints(LOVE, LOVE)







