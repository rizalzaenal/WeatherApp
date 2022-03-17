package com.rizalzaenal.weatherapp.data.mapper

import com.rizalzaenal.weatherapp.data.model.LocationEntity
import com.rizalzaenal.weatherapp.domain.model.Location
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LocationEntityMapperTest {
    private val locationEntity = LocationEntity(
        "a", 1.0, 2.0, "b", "c"
    )

    private val location = Location(
        "a", 1.0, 2.0, "b", "c"
    )

    private lateinit var mapper: LocationEntityMapper

    @Before
    fun setUp() {
        mapper = LocationEntityMapper()
    }

    @Test
    fun `test locationEntity mapper to domain model`() {
        val domainLocation = mapper.toDomainModel(locationEntity)

        assertEquals(domainLocation.name, locationEntity.name)
        assertEquals(domainLocation.state, locationEntity.state)
        assertEquals(domainLocation.country, locationEntity.country)
        assertEquals(domainLocation.lat, locationEntity.lat, 0.0)
        assertEquals(domainLocation.lon, locationEntity.lon, 0.0)
    }

    @Test
    fun `test locationEntity mapper from domain model`() {
        val locationEntity = mapper.fromDomainModel(location)

        assertEquals(locationEntity.name, location.name)
        assertEquals(locationEntity.state, location.state)
        assertEquals(locationEntity.country, location.country)
        assertEquals(locationEntity.lat, location.lat, 0.0)
        assertEquals(locationEntity.lon, location.lon, 0.0)

    }
}