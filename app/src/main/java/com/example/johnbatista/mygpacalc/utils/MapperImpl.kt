package com.example.johnbatista.mygpacalc.utils

import com.example.johnbatista.mygpacalc.data.interfaces.MapperInterface
import com.example.johnbatista.mygpacalc.data.models.Course
import com.example.johnbatista.mygpacalc.local_db.CourseEntity
import javax.inject.Inject

class MapperImpl
@Inject constructor() : MapperInterface<CourseEntity, Course> {

    override fun mapToCache(model: Course): CourseEntity {
        return CourseEntity(
            id = model.id,
            name = model.name,
            unitLoad = model.unitLoad,
            grade = model.grade,
            details = ""
        )
    }

    override fun mapFromCache(model: CourseEntity): Course {
        return Course(id = model.id, model.name, model.unitLoad, model.grade)
    }

    override fun mapAllToCache(model: List<Course>): List<CourseEntity> {
        val cache = mutableListOf<CourseEntity>()
        model.forEach {
            cache.add(mapToCache(it))
        }
        return cache
    }

    override fun mapAllFromCache(model: List<CourseEntity>): List<Course> {
        val cache = mutableListOf<Course>()
        model.forEach {
            cache.add(mapFromCache(it))
        }
        return cache
    }
}