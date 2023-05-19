package com.yas.drugproductservice.service

import com.yas.drugproductservice.datasource.DrugDataSource
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

internal class DrugServiceTest {

    private val dataSource: DrugDataSource = mockk(relaxed = true)
    private val drugService = DrugService(dataSource)

    @Test
    fun `should call to the data source to get drugs`() {

        // every { dataSource.retrieveDrugs() } returns emptyList()
        drugService.getDrugs()
        verify { dataSource.retrieveDrugs() }
    }
}