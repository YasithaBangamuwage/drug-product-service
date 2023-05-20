package com.yas.drugproductservice.datasource.mock

import com.yas.drugproductservice.datasource.DrugDataSource
import com.yas.drugproductservice.model.Drug
import org.springframework.stereotype.Repository

@Repository
class MockDrugDataSource : DrugDataSource {

    val drugs = mutableListOf(
        Drug("1", "Panadol", "ml", true),
        Drug("2", "Brufen", "g", true),
        Drug("3", "Vitamin c", "mg", true)
    )

    override fun retrieveDrugs(): Collection<Drug> = this.drugs

    override fun retrieveDrug(drugId: String): Drug = drugs.firstOrNull { it.id == drugId }
        ?: throw NoSuchElementException("Could not found the drug with id $drugId")

    override fun createDrug(newDrug: Drug): Drug {

        if (drugs.any { it.id == newDrug.id }) {
            throw IllegalArgumentException("Drug with id ${newDrug.id} already exists")
        } else {
            drugs.add(newDrug)
        }

        return newDrug
    }

}