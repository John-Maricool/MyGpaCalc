package com.example.johnbatista.mygpacalc.local_db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GpResult(
    @PrimaryKey(autoGenerate = false)
    var details: String,
    var gp: String = "",
    var totalUl: Int = 0,
    var totalCourses: Int = 0
)