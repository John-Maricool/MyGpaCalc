package com.maricool.johnbatista.mygpacalc.data.models

import android.os.Parcelable
import com.maricool.johnbatista.mygpacalc.utils.getComment4pt
import com.maricool.johnbatista.mygpacalc.utils.getComment5pt
import com.maricool.johnbatista.mygpacalc.utils.getGradeDigit4pt
import com.maricool.johnbatista.mygpacalc.utils.getGradeDigit5Pt
import kotlinx.parcelize.Parcelize

@Parcelize
data class GpResultModel(
    val name: String,
    val gp: String,
    val numOfCourses: Int,
    val totalUl: Int
) : Parcelable {

    fun getComment(type: Int): String{
        return if(type == 5){
            getComment5pt(gp)
        }else{
            getComment4pt(gp)
        }
    }
}