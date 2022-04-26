package com.example.johnbatista.mygpacalc.data.interfaces

interface MapperInterface<CacheModel, DomainModel> {

    fun mapToCache(model: DomainModel): CacheModel
    fun mapFromCache(model: CacheModel): DomainModel
    fun mapAllToCache(model: List<DomainModel>): List<CacheModel>
    fun mapAllFromCache(model: List<CacheModel>): List<DomainModel>
}