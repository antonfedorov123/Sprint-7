package ru.sber.orm.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.sber.orm.entities.Model

@Repository
interface ModelRepositories : CrudRepository<Model, Long> {
}