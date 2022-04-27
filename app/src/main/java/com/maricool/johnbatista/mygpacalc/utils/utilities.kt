package com.maricool.johnbatista.mygpacalc.utils

import android.widget.Spinner
import com.maricool.johnbatista.mygpacalc.R

fun Spinner.getUnitLoadIndex(load: String): Int {
    val regionsInArray = resources.getStringArray(R.array.ul)
    var index = 0
    for (i in regionsInArray.indices) {
        if (regionsInArray[i] == load) {
            index = i
        }
    }
    return index
}

fun Spinner.getGradeIndex(grade: String): Int {
    val regionsInArray = resources.getStringArray(R.array.grades)
    var index = 0
    for (i in regionsInArray.indices) {
        if (regionsInArray[i] == grade) {
            index = i
        }
    }
    return index
}


fun isInputValid(text: String?): Boolean {
    return text != null && text.trim().isNotEmpty()
}