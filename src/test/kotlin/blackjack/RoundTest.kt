package blackjack

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import java.lang.IllegalStateException

class RoundTest {
    @Test
    fun `숫자 계산은 카드의 숫자를 기본으로 한다`() {
        val cards = listOf(
            Card.from(CardType.SPADE, CardValue.TWO),
            Card.from(CardType.CLOVER, CardValue.THREE),
            Card.from(CardType.DIAMOND, CardValue.SIX),
            Card.from(CardType.HEART, CardValue.EIGHT),
        )
        Round.calculateSum(cards) shouldBe 19
    }

    @Test
    fun `J, Q, K는 10으로 계산한다`() {
        val cards = listOf(
            Card.from(CardType.SPADE, CardValue.TWO),
            Card.from(CardType.CLOVER, CardValue.THREE),
            Card.from(CardType.DIAMOND, CardValue.SIX),
            Card.from(CardType.SPADE, CardValue.KING)
        )
        Round.calculateSum(cards) shouldBe 21
    }

    @Test
    fun `A는 1로 계산할 수 있다`() {
        val cards = listOf(
            Card.from(CardType.SPADE, CardValue.TWO),
            Card.from(CardType.DIAMOND, CardValue.EIGHT),
            Card.from(CardType.SPADE, CardValue.ACE)
        )
        Round.calculateSum(cards) shouldBe 21
    }

    @Test
    fun `A는 11로 계산할 수 있다`() {
        val cards = listOf(
            Card.from(CardType.SPADE, CardValue.JACK),
            Card.from(CardType.DIAMOND, CardValue.KING),
            Card.from(CardType.SPADE, CardValue.ACE)
        )
        Round.calculateSum(cards) shouldBe 21
    }

    @Test
    fun `카드는 52장 뽑을 수 있다`() {
        val cards = mutableListOf<Card>()
        val round = Round()

        repeat(52) {
            cards.add(round.getCard())
        }

        cards.size shouldBe 52

        // 52장 중복이 없는지 확인
        cards.toSet().size shouldBe 52

        shouldThrow<IllegalStateException> {
            round.getCard()
        }
    }
}