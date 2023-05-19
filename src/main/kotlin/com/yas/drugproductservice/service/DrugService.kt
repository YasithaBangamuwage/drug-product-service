package com.yas.drugproductservice.service

import com.yas.drugproductservice.datasource.DrugDataSource
import com.yas.drugproductservice.model.Drug
import org.springframework.stereotype.Service

@Service
class DrugService(val dataSource : DrugDataSource) {
    fun getDrugs() : Collection<Drug> = dataSource.retrieveDrugs()
}