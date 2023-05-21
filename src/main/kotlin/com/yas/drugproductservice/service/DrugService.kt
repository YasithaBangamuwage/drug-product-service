package com.yas.drugproductservice.service

import com.yas.drugproductservice.datasource.DrugDataSource
import com.yas.drugproductservice.model.Drug
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
class DrugService(@Qualifier("mock") val dataSource : DrugDataSource) {

    fun getDrugs() : Collection<Drug> {
        return dataSource.retrieveDrugs()
    }

    fun getDrug(drugId: String): Drug =  dataSource.retrieveDrug(drugId)

    fun addDrug(newDrug: Drug): Drug = dataSource.createDrug(newDrug)

    fun updateDrug(updateDrug: Drug) : Drug = dataSource.updateDrug(updateDrug)

    fun deleteDrug(drugId: String) : Unit = dataSource.deleteDrug(drugId)
}