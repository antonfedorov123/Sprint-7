package com.example.demo.controller

import com.example.demo.persistance.Entity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class Controller {

    @GetMapping("hello")
    fun hello(): String = "Hello!"

    @GetMapping("person")
    fun showPerson(): Entity = Entity(1, "Bob")

}