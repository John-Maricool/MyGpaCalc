package com.maricool.johnbatista.mygpacalc.data.repository

import com.maricool.johnbatista.mygpacalc.data.models.GpResultModel
import com.maricool.johnbatista.mygpacalc.local_db.CourseDao
import com.maricool.johnbatista.mygpacalc.utils.MapperResultImpl
import javax.inject.Inject

class PreviousGpsRepository
@Inject constructor(val dao: CourseDao, val mapper: MapperResultImpl) {

    suspend fun getAllSavedGps(): List<GpResultModel> {
        val result = dao.getAllResults()
        return mapper.mapAllFromCache(result)
    }

    suspend fun deleteOne(details: String) {
        dao.deleteGpWithCascade(details)
    }

    suspend fun deleteAll() {
        dao.deleteAllGpWithCascade()
    }
}