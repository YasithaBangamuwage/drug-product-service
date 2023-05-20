package com.yas.drugproductservice.controller

import com.yas.drugproductservice.model.Drug
import com.yas.drugproductservice.service.DrugService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/drugs")
class DrugController(private val service: DrugService) {

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFound(e: NoSuchElementException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.NOT_FOUND)

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleBadRequest(e: IllegalArgumentException): ResponseEntity<String> =
        ResponseEntity(e.message, HttpStatus.BAD_REQUEST)

    @GetMapping
    fun getDrugs(): Collection<Drug> = service.getDrugs()

    @GetMapping("/{drugId}")
    fun getDrug(@PathVariable drugId: String): Drug = service.getDrug(drugId)

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun addDrug(@RequestBody newDrug: Drug): Drug = service.addDrug(newDrug)
}