package com.example.demo.controller

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(Controller::class)
internal class ControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun helloTest() {
        val result = mockMvc.perform(MockMvcRequestBuilders.get(("/hello")))
        result
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("Hello!"))
    }

    @Test
    fun showPersonTest() {
        val result = mockMvc.perform(MockMvcRequestBuilders.get(("/person")))

        result
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.content().string("{\"id\":1,\"name\":\"Bob\"}"))

    }
}