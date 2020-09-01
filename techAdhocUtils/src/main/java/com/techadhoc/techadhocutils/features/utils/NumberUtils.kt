package com.techadhoc.techadhocutils.features.utils

import java.util.regex.Pattern

object NumberUtils {
    private val patternInteger: Pattern = Pattern.compile("-?\\d+(\\.\\d+)?")

    @JvmStatic
   fun isNumeric(strNum: String?): Boolean {
        return if (strNum == null) {
            false
        } else patternInteger.matcher(strNum).matches()
    }
}