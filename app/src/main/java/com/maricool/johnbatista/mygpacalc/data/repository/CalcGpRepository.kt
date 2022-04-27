package com.maricool.johnbatista.mygpacalc.data.repository

import com.maricool.johnbatista.mygpacalc.data.models.Course
import com.maricool.johnbatista.mygpacalc.data.models.GpResultModel
import com.maricool.johnbatista.mygpacalc.local_db.CourseDao
import com.maricool.johnbatista.mygpacalc.utils.MapperImpl
import com.maricool.johnbatista.mygpacalc.utils.MapperResultImpl
import javax.inject.Inject

class CalcGpRepository
@Inject constructor(
    private val mapper: MapperImpl,
    private val mapperRes: MapperResultImpl,
    private val dao: CourseDao
) {

    suspend fun getAllCourses(details: String): List<Course> {
        val courses = dao.getAllCourses(details)
        return if (courses.isNotEmpty()) {
            val res = mapper.mapAllFromCache(courses[0].courses)
            res
        } else {
            listOf()
        }
    }

    suspend fun addGpToDb(result: GpResultModel) {
        val res = mapperRes.mapToCache(result)
        dao.updateGpa(res)
    }

    suspend fun deleteCourse(course: Course, details: String) {
        val res = mapper.mapToCache(course)
        res.details = details
        dao.delete(res)
    }

    suspend fun deleteAllCourse() {
        dao.deleteAllCourse()
    }

}