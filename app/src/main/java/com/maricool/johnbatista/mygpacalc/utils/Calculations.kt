package com.maricool.johnbatista.mygpacalc.utils

fun getGradeDigit(grade: String): Float {
    var dig = 0.0
    when (grade) {
        "A" -> {
            dig = 5.0
        }
        "B" -> {
            dig = 4.0
        }
        "C" -> {
            dig = 3.0
        }
        "D" -> {
            dig = 2.0
        }
        "E" -> {
            dig = 1.0
        }
        "F" -> {
            dig = 0.0
        }
        else -> {
            dig = 0.0
        }
    }
    return dig.toFloat()
}

fun calcGp(totUl: Float, mult: Float): Float {
    return (mult / totUl)
}

fun getComment(gp: String): String {
    val gpa = gp.toFloat()
    return when {
        gpa >= 4.5 -> "First Class, Excellent"
        gpa in 3.5..4.49 -> "Second Class upper"
        gpa in 2.5..3.49 -> "Second Class lower"
        gpa in 1.5..2.49 -> "Third Class"
        gpa in 1.0..1.49 -> "Pass"
        else -> "Error"
    }
}
