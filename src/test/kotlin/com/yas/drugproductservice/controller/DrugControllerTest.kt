package com.yas.drugproductservice.controller

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy.Content
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc
internal class DrugControllerTest {

    @Autowired
    lateinit var mockMvc: MockMvc
    val baseUrl = "/api/drugs/"

    @Test
    fun `should return all drugs`() {
        mockMvc.get(baseUrl).andDo { print() }.andExpect {
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
        }
    }


    @Test
    fun `should return not found if given id does not have drug`() {
        val drugId = "does_not_exist"
        mockMvc.get("$baseUrl/$drugId").andDo { print() }.andExpect {
            status { isNotFound() }
        }
    }
}