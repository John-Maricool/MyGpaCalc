package com.maricool.johnbatista.mygpacalc.data.repository

import com.maricool.johnbatista.mygpacalc.local_db.CourseDao
import com.maricool.johnbatista.mygpacalc.local_db.GpResult
import javax.inject.Inject

class FirstScreenRepository
@Inject constructor(val dao: CourseDao) {

    suspend fun insertGpDetails(gpa: GpResult): Boolean {
        val det = dao.getAllCourseDetails()
        return if (det.contains(gpa.details)) {
            false
        } else {
            dao.insertGpa(gpa)
            true
        }
    }
}