package com.rizalzaenal.weatherapp.data.mapper

import com.rizalzaenal.weatherapp.data.model.LocationEntity
import com.rizalzaenal.weatherapp.domain.model.Location

class LocationEntityMapper: Mapper<LocationEntity, Location> {
    override fun toDomainModel(data: LocationEntity): Location {
        return Location(
            data.country,
            data.lat,
            data.lon,
            data.name,
            data.state
        )
    }

    override fun fromDomainModel(data: Location): LocationEntity {
        return LocationEntity(
            data.country,
            data.lat,
            data.lon,
            data.name,
            data.state
        )
    }
}