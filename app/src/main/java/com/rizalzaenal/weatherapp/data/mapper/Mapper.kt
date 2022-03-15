package com.rizalzaenal.weatherapp.data.mapper

interface Mapper<Data, Domain> {
    fun toDomainModel(data: Data): Domain
    fun fromDomainModel(data: Domain): Data
}