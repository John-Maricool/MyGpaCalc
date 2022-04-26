package com.example.johnbatista.mygpacalc.local_db

import androidx.room.Embedded
import androidx.room.Relation

data class ResultWithCourses (
    @Embedded val result: GpResult,
    @Relation(
        parentColumn = "details",
        entityColumn = "details"
    )
    val courses: List<CourseEntity>
)