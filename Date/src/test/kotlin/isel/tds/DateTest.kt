package isel.tds

import org.example.isel.tds.Date
import org.example.isel.tds.compareTo
import org.example.isel.tds.isLeapYear
import org.example.isel.tds.plus
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

        assertFailsWith<IllegalArgumentException> { Date(1000)  }
        assertFailsWith<IllegalArgumentException> { Date(2024,0)  }
        assertFailsWith<IllegalArgumentException> { Date(2024,13)  }
        assertFailsWith<IllegalArgumentException> { Date(2024, 9, 31)  }
        assertFailsWith<IllegalArgumentException> { Date(2024, 2, 30)  }
        assertFailsWith<IllegalArgumentException> { Date(2023, 2, 29)  }

    }

    @Test
    fun testAddDaysToDateTest(){
        val date = Date(2024, 9, 20) + 5

        assertEquals(2024, date.year, "Year not valid")
        assertEquals(9, date.month)
        assertEquals(25, date.day)
    }

    @Test
    fun testAddDateToDaysTest(){
        val date = 5 + Date(2024, 9, 20)

        assertEquals(2024, date.year, "Year not valid")
        assertEquals(9, date.month)
        assertEquals(25, date.day)
    }

    @Test
    fun testAddDaysToDateWithMonthIncrementTest(){
        val date = Date(2024, 9, 20) + 15

        assertEquals(2024, date.year, "Year not valid")
        assertEquals(10, date.month)
        assertEquals(5, date.day)
    }

    @Test
    fun testAddDaysToDateWith2MonthIncrementTest(){
        val date = Date(2024, 9, 20) + 45

        assertEquals(2024, date.year, "Year not valid")
        assertEquals(11, date.month)
        assertEquals(4, date.day)
    }

    @Test
    fun testAddDaysToDateWithYearIncrementTest(){
        val date = Date(2024, 9, 20) + 107

        assertEquals(2025, date.year, "Year not valid")
        assertEquals(1, date.month)
        assertEquals(5, date.day)
    }


    @Test fun `Test equality of dates that represent the same date`() {
        val sut = Date(2023, 3, 2)
        assertEquals(sut, sut)             //   => assertTrue(sut == sut)
        assertEquals(sut, Date(2023, 3, 2))
    }


    @Test fun `Tests inequality between dates and dates with another type`() {
        val sut = Date(2023, 3, 2)
        assertNotEquals(sut, Date(2023, 4, 1))
        val any :Int = 2023
        assertNotEquals(sut, any)  // Compare with Int
        val dn :Date? = null
        assertNotEquals(sut, dn)   // Compare with null
        assertNotEquals(dn, sut)   // Compare null with Date
    }


    @Test fun `Coherence between equals and hashCode`() {
        val sut = Date(2023, 3, 2)
        assertEquals(sut.hashCode(), sut.hashCode())
        val d2 = Date(2023, 3, 2)
        assertEquals(sut, d2)
        assertEquals(sut.hashCode(), d2.hashCode()) // Must be the same
        val d3 = Date(2023, 4, 2)
        assertNotEquals(sut, d3)
        assertNotEquals(sut.hashCode(), d3.hashCode()) // Should be different
    }


    @Test fun `Compare dates`() {
        val sut = Date(2023, 3, 2)
        assertTrue(sut < Date(2023, 3, 4))
        assertTrue(Date(2023, 4, 2) >= sut)
        assertTrue(sut <= Date(2024, 3, 2))
        assertTrue(sut > Date(2023, 3, 1))
    }

    @Test fun `String representation of a date`() {
        val sut = Date(2023, 3, 2)
        assertEquals("2023-03-02", sut.toString())
    }
}




