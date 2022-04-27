package com.maricool.johnbatista.mygpacalc.local_db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CourseEntity::class, GpResult::class], version = 1, exportSchema = false)
abstract class CourseDatabase: RoomDatabase() {

    abstract fun courseDao(): CourseDao
}