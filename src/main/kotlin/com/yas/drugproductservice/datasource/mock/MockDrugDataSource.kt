package com.yas.drugproductservice.datasource.mock

import com.yas.drugproductservice.datasource.DrugDataSource
import com.yas.drugproductservice.model.Drug
import org.springframework.stereotype.Repository

@Repository
class MockDrugDataSource : DrugDataSource {

    val drugs = listOf(
        Drug("1", "Panadol", "ml", true),
        Drug("2", "Brufen", "g", true),
        Drug("3", "Vitamin c", "mg", true)
    )

    override fun retrieveDrugs(): Collection<Drug> = drugs

}