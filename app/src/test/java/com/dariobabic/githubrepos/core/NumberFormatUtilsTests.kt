package com.dariobabic.githubrepos.core

import com.dariobabic.githubrepos.core.utils.formatCounter
import org.junit.Assert.assertEquals
import org.junit.Test

class NumberFormatUtilsTests {

    @Test
    fun formatCounter_pass1000000_returns1M() {
        // GIVEN
        val number = 1000000
        val expected = "1M"
        // WHEN
        val actual = formatCounter(number)
        // THEN
        assertEquals(expected, actual)
    }

    @Test
    fun formatCounter_pass999999_returns999k() {
        // GIVEN
        val number = 999999
        val expected = "999k"
        // WHEN
        val actual = formatCounter(number)
        // THEN
        assertEquals(expected, actual)
    }

    @Test
    fun formatCounter_pass1000_returns1k() {
        // GIVEN
        val number = 1000
        val expected = "1k"
        // WHEN
        val actual = formatCounter(number)
        // THEN
        assertEquals(expected, actual)
    }

    @Test
    fun formatCounter_pass999_returns999() {
        // GIVEN
        val number = 999
        val expected = "999"
        // WHEN
        val actual = formatCounter(number)
        // THEN
        assertEquals(expected, actual)
    }

    @Test
    fun `formatCounter_pass1333_returns1,3k`() {
        // GIVEN
        val number = 1333
        val expected = "1.3k"
        // WHEN
        val actual = formatCounter(number)
        // THEN
        assertEquals(expected, actual)
    }

    @Test
    fun formatCounter_passNegativeNumber_returnsNegativeNumber() {
        // GIVEN
        val number = -1333
        val expected = "-1333"
        // WHEN
        val actual = formatCounter(number)
        // THEN
        assertEquals(expected, actual)
    }

    @Test
    fun formatCounter_passIntMin_returnsIntMinPlusOne() {
        // GIVEN
        val number = Int.MIN_VALUE
        val expected = "-2147483647"
        // WHEN
        val actual = formatCounter(number)
        // THEN
        assertEquals(expected, actual)
    }

    @Test
    fun formatCounter_passIntMax_returnsIntMaxMinusOne() {
        // GIVEN
        val number = Int.MAX_VALUE
        val expected = "2147483646"
        // WHEN
        val actual = formatCounter(number)
        // THEN
        assertEquals(expected, actual)
    }
}