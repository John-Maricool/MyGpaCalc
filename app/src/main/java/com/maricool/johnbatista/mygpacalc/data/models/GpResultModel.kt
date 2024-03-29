package com.maricool.johnbatista.mygpacalc.data.models

import android.os.Parcelable
import com.maricool.johnbatista.mygpacalc.utils.getComment
import kotlinx.parcelize.Parcelize

@Parcelize
data class GpResultModel(
    val name: String,
    val gp: String,
    val numOfCourses: Int,
    val totalUl: Int
) : Parcelable {
    val comm: String
        get() = getComment(gp)
}