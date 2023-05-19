package com.yas.drugproductservice.datasource

import com.yas.drugproductservice.model.Drug

interface DrugDataSource {

    fun retrieveDrugs(): Collection<Drug>
}