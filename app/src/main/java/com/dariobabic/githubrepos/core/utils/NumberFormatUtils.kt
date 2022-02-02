package com.dariobabic.githubrepos.core.utils

import com.dariobabic.githubrepos.core.constants.DASH
import java.util.*

private val suffixes: NavigableMap<Int, String> = TreeMap(mapOf(1_000 to "k", 1_000_000 to "M"))

fun formatCounter(number: Int): String {
    if (number == Int.MIN_VALUE) return String.format("${Int.MIN_VALUE + 1}")
    if (number == Int.MAX_VALUE) return String.format("${Int.MAX_VALUE - 1}")
    if (number < 0) return DASH + String.format((-number).toString())
    if (number < 1000) return number.toString()

    val pair = suffixes.floorEntry(number) ?: return number.toString()
    val divideBy = pair.key
    val suffix = pair.value

    val truncated = number / (divideBy / 10)

    val hasDecimal = truncated < 100 && truncated / 10.0 != (truncated / 10).toDouble()
    return if (hasDecimal) (truncated / 10.0).toString() + suffix else (truncated / 10).toString() + suffix
}