package com.example.demo.controller

import com.example.demo.persistance.Entity
import com.example.demo.persistance.EntityRepository
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class EntityRepositoryTest {

    @Autowired
    private lateinit var entityRepository: EntityRepository

    @Test
    fun `save and find entity`() {
        val entity = entityRepository.save(Entity(name = "Bob"))

        val founded = entityRepository.findById(entity.id!!)

        assertTrue(founded.get() == entity)
    }

}