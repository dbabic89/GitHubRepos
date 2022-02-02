package com.dariobabic.githubrepos.core

import com.dariobabic.githubrepos.core.utils.formatDate
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class DateUtilsTests {

    @Test
    fun formatDate_passGermanLocale_returnDayMonthYear() {
        // GIVEN
        val date = "2012-02-13T17:29:58Z"
        val expected = "13.02.2012"
        // WHEN
        val actual = formatDate(date, Locale.GERMANY)
        // THEN
        assertEquals(expected, actual)
    }
}