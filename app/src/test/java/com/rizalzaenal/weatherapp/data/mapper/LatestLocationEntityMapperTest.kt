package com.rizalzaenal.weatherapp.data.mapper

import com.rizalzaenal.weatherapp.data.model.LatestLocationEntity
import com.rizalzaenal.weatherapp.domain.model.Location
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class LatestLocationEntityMapperTest {
    private val latestLocationEntity = LatestLocationEntity(
        "a", 1.0, 2.0, "b", "c"
    )

    private val location = Location(
        "a", 1.0, 2.0, "b", "c"
    )

    private lateinit var mapper: LatestLocationEntityMapper

    @Before
    fun setUp() {
        mapper = LatestLocationEntityMapper()
    }

    @Test
    fun `test latestLocationEntity mapper to domain model`() {
        val domainLocation = mapper.toDomainModel(latestLocationEntity)

        assertEquals(domainLocation.name, latestLocationEntity.name)
        assertEquals(domainLocation.state, latestLocationEntity.state)
        assertEquals(domainLocation.country, latestLocationEntity.country)
        assertEquals(domainLocation.lat, latestLocationEntity.lat, 0.0)
        assertEquals(domainLocation.lon, latestLocationEntity.lon, 0.0)
    }

    @Test
    fun `test latestLocationEntity mapper from domain model`() {
        val latestLocation = mapper.fromDomainModel(location)

        assertEquals(latestLocation.name, location.name)
        assertEquals(latestLocation.state, location.state)
        assertEquals(latestLocation.country, location.country)
        assertEquals(latestLocation.lat, location.lat, 0.0)
        assertEquals(latestLocation.lon, location.lon, 0.0)
    }
}