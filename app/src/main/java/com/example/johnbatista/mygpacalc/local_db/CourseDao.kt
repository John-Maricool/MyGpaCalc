package com.example.johnbatista.mygpacalc.local_db

import androidx.room.*

@Dao
interface CourseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: CourseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertGpa(gpa: GpResult)

    @Update
    suspend fun updateGpa(gpa: GpResult)

    @Update
    suspend fun updateCourse(course: CourseEntity)

    @Query("delete from CourseEntity")
    suspend fun deleteCourses()

    @Transaction
    @Query("select * from GpResult where details = :det")
    suspend fun getAllCourses(det: String): List<ResultWithCourses>

    @Query("select details from GpResult")
    suspend fun getAllCourseDetails(): List<String>

    @Delete
    suspend fun delete(course: CourseEntity)

    @Query("delete from courseentity where details = :details")
    suspend fun deleteCourse(details: String)

    @Query("delete from GpResult where details = :details")
    suspend fun deleteGpResult(details: String)

    @Query("delete  from courseentity ")
    suspend fun deleteAllCourse()

    @Query("delete from GpResult")
    suspend fun deleteAllGpResult()

    @Transaction
    @Query("")
    suspend fun deleteGpWithCascade(details: String) {
        deleteCourse(details)
        deleteGpResult(details)
    }

    @Transaction
    @Query("")
    suspend fun deleteAllGpWithCascade() {
        deleteAllCourse()
        deleteAllGpResult()
    }

    @Query("select * from GpResult")
    suspend fun getAllResults(): List<GpResult>
}