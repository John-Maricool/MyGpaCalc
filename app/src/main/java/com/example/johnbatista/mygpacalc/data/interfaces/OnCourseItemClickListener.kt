package com.example.johnbatista.mygpacalc.data.interfaces

import com.example.johnbatista.mygpacalc.data.models.Course

interface OnCourseItemClickListener {
    fun onCourseItemClick(course: Course)
    fun onCourseLongClick(course: Course): Boolean
}



