package com.maricool.johnbatista.mygpacalc.data.models

import android.os.Parcelable
import com.maricool.johnbatista.mygpacalc.utils.getGradeDigit
import kotlinx.parcelize.Parcelize

@Parcelize
data class Course(
    var id: Int,
    val name: String = "",
    val unitLoad: Int = 0,
    val grade: String
) : Parcelable {
    val gradeDigit: Float
        get() = getGradeDigit(grade)
}