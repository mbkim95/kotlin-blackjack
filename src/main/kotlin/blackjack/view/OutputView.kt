package blackjack.view

import blackjack.domain.model.Dealer
import blackjack.domain.model.Player

object OutputView {
    fun divideCard(names: List<String>) {
        println("${names.joinToString(",")}에게 2장의 카드를 나누었습니다.")
    }

    fun printPlayersCards(players: List<Player>, isResult: Boolean = false) {
        if (isResult) println()
        players.forEach {
            printCards(it, isResult)
        }
        println()
    }

    fun printCards(player: Player, isResult: Boolean = false) {
        val cards = if (player is Dealer && !isResult) {
            val card = player.cards.items[0]
            "${card.value.text}${card.type.text}"
        } else player.cards.items.joinToString { "${it.value.text}${it.type.text}" }
        print("${player.info.name}${if (player is Dealer) "" else "카드"}: $cards")

        if (isResult) {
            print(" - 결과: ${player.cards.sum}")
        }
        println()
    }

    fun printDealerGetCard() = println("\n딜러는 16이하라 한장의 카드를 더 받았습니다.")

    fun printResult(players: List<Player>) {
        println("\n## 최종 승패")
        players.forEach {
            printScore(it)
        }
    }

    private fun printScore(player: Player) {
        val record: String = if (player is Dealer) dealerRecord(player) else playerRecord(player)
        println("${player.info.name}: ${record.ifBlank { "무승부" }}")
    }

    private fun dealerRecord(dealer: Dealer): String {
        val win = dealer.info.record.win
        val lose = dealer.info.record.lose
        return "${if (win > 0) "${win}승" else ""} ${if (lose > 0) "${lose}패" else ""}".trim()
    }

    private fun playerRecord(player: Player): String {
        val win = player.info.record.win
        val lose = player.info.record.lose
        return "${if (win > 0) "승" else ""} ${if (lose > 0) "패" else ""}".trim()
    }
}
