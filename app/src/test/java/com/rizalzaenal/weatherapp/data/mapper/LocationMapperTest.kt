package com.rizalzaenal.weatherapp.data.mapper

import com.rizalzaenal.weatherapp.data.model.LocationsResponse
import org.junit.Assert.*
import org.junit.Test

class LocationMapperTest {
    private val dataLocation = LocationsResponse(
        "a", 1.0, 2.0, "b", "c"
    )

    @Test
    fun `test location mapper to domain model`() {
        val mapper = LocationMapper()
        val domainLocation = mapper.toDomainModel(dataLocation)

        assertEquals(domainLocation.name, dataLocation.name)
        assertEquals(domainLocation.state, dataLocation.state)
        assertEquals(domainLocation.country, dataLocation.country)
        assertEquals(domainLocation.lat, dataLocation.lat!!, 0.0)
        assertEquals(domainLocation.lon, dataLocation.lon!!, 0.0)
    }
}