package isel.tds

import org.example.isel.tds.Date
import org.example.isel.tds.isLeapYear
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class DateTest {
    @Test
    fun testDateCreation() {
        val date = Date(2024, 9, 18)

        assertEquals(2024, date.year, "Year not valid")
        assertEquals(9, date.month)
        assertEquals(18, date.day)
    }

    @Test
    fun `test Date Creation With Day Default Value`() {
        val date = Date(2024, 9)

        assertEquals(2024, date.year, "Year not valid")
        assertEquals(9, date.month)
        assertEquals(1, date.day)
    }

    @Test
    fun `test Date Creation With Day and Month Default Value`() {
        val date = Date(2024)

        assertEquals(2024, date.year, "Year not valid")
        assertEquals(1, date.month)
        assertEquals(1, date.day)
    }

    @Test
    fun `test Date Creation With Day and Year Default Value`() {
        val sut = Date(2024, day = 18)

        assertEquals(2024, sut.year, "Year not valid")
        assertEquals(1, sut.month)
        assertEquals(18, sut.day)
    }

    @Test
    fun `test Leap Year`() {
        val sut = Date(2024)

        assertTrue(sut.isLeapYear())
    }

    @Test
    fun `test Isn't Leap Year`() {
        val sut = Date(2023)

        assertFalse(sut.isLeapYear())
//        assertFalse(2023.isLeapYear())
    }

    @Test
    fun `test Last Day Of Month`() {
        val sut = Date(2023, 2)
        assertEquals(28, sut.lastDayOfMonth, "Error in Date that isn't a LeapYear")

        val sutLeapYear = Date(2024, 2)
        assertEquals(29, sutLeapYear.lastDayOfMonth, "Error in Date that is a LeapYear")
    }

    @Test
    fun `test Invalid State`() {

        assertFailsWith<IllegalArgumentException> { Date(2024,0)  }


    }
}