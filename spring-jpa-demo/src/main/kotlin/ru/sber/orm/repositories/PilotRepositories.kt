package ru.sber.orm.repositories

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import ru.sber.orm.entities.Pilot

@Repository
interface PilotRepositories : CrudRepository<Pilot, Long> {

    fun findByName(name: String): Pilot

}