package com.rizalzaenal.weatherapp.data.mapper

import com.rizalzaenal.weatherapp.data.model.LocationsResponse
import com.rizalzaenal.weatherapp.domain.model.Location
import com.rizalzaenal.weatherapp.utils.replaceNull

class LocationMapper: Mapper<LocationsResponse, Location> {
    override fun toDomainModel(data: LocationsResponse): Location {
        return Location(
            data.country.replaceNull(),
            data.lat.replaceNull(),
            data.lon.replaceNull(),
            data.name.replaceNull(),
            data.state.replaceNull()
        )
    }

    override fun fromDomainModel(data: Location): LocationsResponse {
        TODO("Not yet implemented")
    }
}