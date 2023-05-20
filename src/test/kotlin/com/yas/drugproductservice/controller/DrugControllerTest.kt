package com.yas.drugproductservice.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.yas.drugproductservice.model.Drug
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureMockMvc
internal class DrugControllerTest @Autowired constructor(val mockMvc: MockMvc, val objectMapper: ObjectMapper) {

    val baseUrl = "/api/drugs"

    @Test
    fun `should return all drugs`() {
        mockMvc.get("$baseUrl").andDo { print() }.andExpect {
            status { isOk() }
            jsonPath("$[0].id") { value("1") }
            jsonPath("$[1].id") { value("2") }
            jsonPath("$[2].id") { value("3") }
        }
    }

    @Test
    fun `should return drug with given drug id`() {

        val drugId = "1"
        mockMvc.get("$baseUrl/$drugId").andDo { print() }.andExpect {
            status { isOk() }
            jsonPath("$.id") { value("1") }
            jsonPath("$.name") { value("Panadol") }
            jsonPath("$.unit") { value("ml") }
            jsonPath("$.publish") { value(true) }
        }
    }

    @Test
    fun `should return not found if given id does not have drug`() {
        val drugId = "does_not_exist"
        mockMvc.get("$baseUrl/$drugId").andDo { print() }.andExpect {
            status { isNotFound() }
        }
    }

    @Test
    fun `should add new drug`() {

        val newDrug = Drug("10", "name1", "unit1", true)
        val performPost = mockMvc.post("$baseUrl") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(newDrug)
        }

        performPost.andDo { print() }.andExpect { status { isCreated() } }.andExpect {
            jsonPath("$.id") { value("10") }
            jsonPath("$.name") { value("name1") }
            jsonPath("$.unit") { value("unit1") }
            jsonPath("$.publish") { value(true) }
        }
    }

    @Test
    fun `should return BAD_REQUEST when creating drug with existing drug id`() {

        val invalidDrug = Drug("1", "Panadol", "ml", true)
        val performPost = mockMvc.post("$baseUrl") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(invalidDrug)
        }

        performPost.andDo { print() }.andExpect { status { isBadRequest() } }
    }
}