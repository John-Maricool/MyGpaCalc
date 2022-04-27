package com.maricool.johnbatista.mygpacalc.local_db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CourseEntity")
data class CourseEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    var details: String = "",
    val unitLoad: Int,
    val grade: String
) {
}