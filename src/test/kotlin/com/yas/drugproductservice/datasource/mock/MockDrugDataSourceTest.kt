package com.yas.drugproductservice.datasource.mock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MockDrugDataSourceTest {

    private val mockDrugDateSource = MockDrugDataSource()

    @Test
    fun `should provide a collection of drugs`() {
        val drugs = mockDrugDateSource.retrieveDrugs()
        assertThat(drugs).isNotEmpty
    }

    @Test
    fun `should provide some mock data`() {
        val drugs = mockDrugDateSource.retrieveDrugs()

        assertThat(drugs).allMatch { it.id.isNotEmpty() }
        assertThat(drugs).allMatch { it.name.isNotEmpty() }
        assertThat(drugs).allMatch { it.unit.isNotEmpty() }
    }
}