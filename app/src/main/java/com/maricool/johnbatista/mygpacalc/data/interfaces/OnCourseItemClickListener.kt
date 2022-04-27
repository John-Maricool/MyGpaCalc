package com.maricool.johnbatista.mygpacalc.data.interfaces

import com.maricool.johnbatista.mygpacalc.data.models.Course

interface OnCourseItemClickListener {
    fun onCourseItemClick(course: Course)
    fun onCourseLongClick(course: Course): Boolean
}



