package com.maricool.johnbatista.mygpacalc.utils

import com.maricool.johnbatista.mygpacalc.data.interfaces.MapperInterface
import com.maricool.johnbatista.mygpacalc.data.models.GpResultModel
import com.maricool.johnbatista.mygpacalc.local_db.GpResult
import javax.inject.Inject

class MapperResultImpl
@Inject constructor() : MapperInterface<GpResult, GpResultModel> {

    override fun mapToCache(model: GpResultModel): GpResult {
        return GpResult(
            details = model.name,
            gp = model.gp,
            totalUl = model.totalUl,
            totalCourses = model.numOfCourses
        )
    }

    override fun mapFromCache(model: GpResult): GpResultModel {
        return GpResultModel(
            name = model.details,
            gp = model.gp,
            totalUl = model.totalUl,
            numOfCourses = model.totalCourses
        )
    }

    override fun mapAllToCache(model: List<GpResultModel>): List<GpResult> {
        val cache = mutableListOf<GpResult>()
        model.forEach {
            cache.add(mapToCache(it))
        }
        return cache
    }

    override fun mapAllFromCache(model: List<GpResult>): List<GpResultModel> {
        val cache = mutableListOf<GpResultModel>()
        model.forEach {
            cache.add(mapFromCache(it))
        }
        return cache
    }
}