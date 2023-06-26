package blackjack.domain

import blackjack.domain.model.Dealer
import blackjack.domain.model.Player

object Rule {
    const val DEALER_MINIMUM_SCORE = 17
    const val BLACK_JACK = 21

    fun decisionWinner(dealer: Dealer, player: Player): Player? {
        val dealerSum = dealer.cards.sum
        val playerSum = player.cards.sum

        if (isOverBlackJack(dealerSum)) return playerWin(dealer, player)

        if (isOverBlackJack(playerSum)) return dealerWin(dealer, player)

        return when {
            dealerSum > playerSum -> dealerWin(dealer, player)
            dealerSum < playerSum -> playerWin(dealer, player)
            else -> null
        }
    }

    private fun isOverBlackJack(score: Int): Boolean = score > BLACK_JACK

    private fun playerWin(dealer: Dealer, player: Player): Player {
        dealer.lose()
        player.win()
        return player
    }

    private fun dealerWin(dealer: Dealer, player: Player): Dealer {
        dealer.win()
        player.lose()
        return dealer
    }
}
