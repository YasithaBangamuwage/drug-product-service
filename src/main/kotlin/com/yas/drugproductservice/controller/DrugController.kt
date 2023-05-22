package com.yas.drugproductservice.controller

import com.yas.drugproductservice.model.Drug
import com.yas.drugproductservice.service.DrugService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/api/drugs")
class DrugController(
    private val service: DrugService,
    @Autowired private val kafkaTemplate: KafkaTemplate<String, String>
) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getDrugs(): Collection<Drug> {

        kafkaTemplate.send("drugProductService","drugs loading !!")
        return service.getDrugs()
    }

    @GetMapping("/{drugId}")
    fun getDrug(@PathVariable drugId: String): Drug = service.getDrug(drugId)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun addDrug(@RequestBody newDrug: Drug): Drug = service.addDrug(newDrug)

    @PatchMapping
    fun updateDrug(@RequestBody updateDrug: Drug) = service.updateDrug(updateDrug)

    @DeleteMapping("/{drugId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteDrug(@PathVariable drugId: String): Unit = service.deleteDrug(drugId)
}