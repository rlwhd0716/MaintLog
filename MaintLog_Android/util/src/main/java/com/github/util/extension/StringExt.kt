package com.github.util.extension

import android.annotation.SuppressLint
import android.telephony.PhoneNumberUtils
import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.regex.Pattern


fun String.toDateFormat(format: String = "yyyy-MM-dd HH:mm"):String? {
    try {
        return this.formattingDate("yyyyMMddHHmmss", format)
    } catch (e:ParseException) {
        return null
    }
}

fun String.toCallPattern() :String {
    val pattern = "/^\\d{2,3}-\\d{3,4}-\\d{4}\$/"
    val matcher = Pattern.compile(pattern).matcher(this)
    return if (matcher.matches()) this else PhoneNumberUtils.formatNumber(
        this,
        Locale.getDefault().country
    )
}

/**
 * Input DateTime
 * @return (14자리 년 월 일 시 분 초)
 */
fun getDateYYYYMMDDHHmmss(): String? {
    val date = Date()
    val sdfCurTime = SimpleDateFormat("yyyyMMddHHmmss", Locale.US)
    return sdfCurTime.format(date.time)
}

@SuppressLint("SimpleDateFormat")
fun String?.formattingDate(oldFormat: String?, newFormat: String): String? {
    this?.let {
        val old = SimpleDateFormat(oldFormat)
        val new = SimpleDateFormat(newFormat)

        try {
            val oldDate = old.parse(this)
            oldDate?.let {
                return new.format(it)
            }
        } catch (e: ParseException) {
            e.printStackTrace()
            Log.e(javaClass.simpleName, "Ext formattingDate function Error!!! ${e.message}")
        }
    }
    return null
}
