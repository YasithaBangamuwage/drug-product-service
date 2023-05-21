package com.yas.drugproductservice.datasource

import com.yas.drugproductservice.model.Drug

interface DrugDataSource {

    fun retrieveDrugs(): Collection<Drug>

    fun retrieveDrug(drugId : String): Drug

    fun createDrug(newDrug: Drug): Drug

    fun updateDrug(updateDrug: Drug): Drug

    fun deleteDrug(drugId: String): Unit
}