package com.dariobabic.githubrepos.core.utils

import com.dariobabic.githubrepos.core.constants.EMPTY_STRING
import java.text.SimpleDateFormat
import java.util.*

private const val FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z = "yyyy-MM-dd'T'HH:mm:ss'Z'"
private const val FORMAT_DD_MM_YYYY = "dd.MM.yyyy"

fun formatDate(value: String, locale: Locale = Locale.GERMANY): String {
    val parseFormat = SimpleDateFormat(FORMAT_YYYY_MM_DD_T_HH_MM_SS_Z, locale)
    val date = parseFormat.parse(value) ?: return EMPTY_STRING
    return SimpleDateFormat(FORMAT_DD_MM_YYYY, locale).format(date).toString()
}