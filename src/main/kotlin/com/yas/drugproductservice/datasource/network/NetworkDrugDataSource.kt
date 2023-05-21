package com.yas.drugproductservice.datasource.network

import com.yas.drugproductservice.datasource.DrugDataSource
import com.yas.drugproductservice.datasource.network.dto.DrugList
import com.yas.drugproductservice.model.Drug
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity
import java.io.IOException

@Repository("network")
class NetworkDrugDataSource(@Autowired private val restTemplate: RestTemplate) : DrugDataSource {
    override fun retrieveDrugs(): Collection<Drug> {
        val response: ResponseEntity<DrugList> =
            restTemplate.getForEntity("https://crudcrud.com/api/40e108ea8a5d4868a3153ee630b45fff/Drug")
        return response.body?.drugList ?: throw IOException("Could not fetch")

    }

    override fun retrieveDrug(drugId: String): Drug {
        TODO("Not yet implemented")
    }

    override fun createDrug(newDrug: Drug): Drug {
        TODO("Not yet implemented")
    }

    override fun updateDrug(updateDrug: Drug): Drug {
        TODO("Not yet implemented")
    }

    override fun deleteDrug(drugId: String) {
        TODO("Not yet implemented")
    }
}