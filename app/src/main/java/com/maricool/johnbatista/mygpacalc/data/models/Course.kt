package com.maricool.johnbatista.mygpacalc.data.models

import android.os.Parcelable
import com.maricool.johnbatista.mygpacalc.utils.getGradeDigit4pt
import com.maricool.johnbatista.mygpacalc.utils.getGradeDigit5Pt
import kotlinx.parcelize.Parcelize

@Parcelize
data class Course(
    var id: Int,
    val name: String = "",
    val unitLoad: Int = 0,
    val grade: String
) : Parcelable {

    fun getGradeDigit(type: Int): Float{
        return if(type == 5){
            getGradeDigit5Pt(grade)
        }else{
            getGradeDigit4pt(grade)
        }
    }

}