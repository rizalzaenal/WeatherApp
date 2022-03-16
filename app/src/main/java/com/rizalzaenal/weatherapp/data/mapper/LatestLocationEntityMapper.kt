package com.rizalzaenal.weatherapp.data.mapper

import com.rizalzaenal.weatherapp.data.model.LatestLocationEntity
import com.rizalzaenal.weatherapp.domain.model.Location

class LatestLocationEntityMapper: Mapper<LatestLocationEntity, Location> {
    override fun toDomainModel(data: LatestLocationEntity): Location {
        return Location(
            data.country,
            data.lat,
            data.lon,
            data.name,
            data.state
        )
    }

    override fun fromDomainModel(data: Location): LatestLocationEntity {
        return LatestLocationEntity(
            data.country,
            data.lat,
            data.lon,
            data.name,
            data.state
        )
    }
}