package de.rwth.swc.piggybank.domain.shared.valueobject

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class MoneyTest {

    @Test
    fun `test valid money creation`() {
        val currency = Currency.USD
        val amount = 100.0

        val money = Money.from(amount, currency)

        money.amount.value shouldBe 10000L
        money.currency shouldBe currency
    }

    @Test
    fun `test invalid money creation`() {
        val currency = Currency.USD
        val amount = 100.123

        val exception = shouldThrow<IllegalArgumentException> {
            Money.from(amount, currency)
        }

        exception.message shouldBe "Amount cannot be exactly represented in 2 decimal places: 100.123"
    }

    @Test
    fun `test money toString`() {
        val currency = Currency.USD
        val amount = 100.0
        val money = Money.from(amount, currency)

        money.toString() shouldBe "$ 100,00"
    }
}