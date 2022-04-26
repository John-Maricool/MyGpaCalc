package com.example.johnbatista.mygpacalc.data.repository

import com.example.johnbatista.mygpacalc.data.models.Course
import com.example.johnbatista.mygpacalc.local_db.CourseDao
import com.example.johnbatista.mygpacalc.utils.MapperImpl
import javax.inject.Inject

class AddCourseRepository
@Inject constructor(private val dao: CourseDao, private val mappeer: MapperImpl) {

    suspend fun addCourse(course: Course, details: String) {
        val courseEntity = mappeer.mapToCache(course)
        courseEntity.details = details
        dao.insertCourse(courseEntity)
    }

    suspend fun updateCourse(course: Course, details: String) {
        val courseEntity = mappeer.mapToCache(course)
        courseEntity.details = details
        dao.updateCourse(courseEntity)
    }
}