package com.maricool.johnbatista.mygpacalc.data.repository

import android.util.Log
import com.maricool.johnbatista.mygpacalc.data.models.GpResultModel
import com.maricool.johnbatista.mygpacalc.local_db.CourseDao
import com.maricool.johnbatista.mygpacalc.utils.MapperResultImpl
import javax.inject.Inject

class RetriveGpDetailsRepo
@Inject constructor(val dao: CourseDao, val mapper: MapperResultImpl) {

    suspend fun getAllSavedGps(): List<GpResultModel> {
        val result = dao.getAllResults()
        Log.d("result", result.toString())
        return mapper.mapAllFromCache(result)
    }

    fun getTotalSemesters(res: List<GpResultModel>): String{
        Log.d("result", res.size.toString())
        return res.size.toString() + " Semesters"
    }

    fun getTotalCourses(res: List<GpResultModel>): String{
        var total = 0
        if(res.isNotEmpty()) {
            res.forEach {
                total += it.numOfCourses
            }
        }
        Log.d("result", total.toString())
        return total.toString() + " Courses"
    }

    fun getTotalUnitLoads(res: List<GpResultModel>): Int{
        var total = 0
        if(res.isNotEmpty()) {
            res.forEach {
                total += it.totalUl
            }
        }
        Log.d("result", total.toString())
        return total
    }

    fun getAverageGp(res: List<GpResultModel>): String{
        var avgGp = 0.0F
        if(res.isNotEmpty()) {
            res.forEach {
                val gp = it.gp.toFloat()
                avgGp += gp
            }
        }
        Log.d("result", avgGp.toString())
        return String.format("%.3f", (avgGp / res.size))
    }


    suspend fun deleteOne(details: String) {
        dao.deleteGpWithCascade(details)
    }

    suspend fun deleteAll() {
        dao.deleteAllGpWithCascade()
    }
}