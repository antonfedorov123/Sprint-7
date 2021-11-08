package ru.sber.orm

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import ru.sber.orm.entities.Aircraft
import ru.sber.orm.entities.Model
import ru.sber.orm.entities.Pilot
import ru.sber.orm.repositories.AircraftRepositories
import ru.sber.orm.repositories.ModelRepositories
import ru.sber.orm.repositories.PilotRepositories

@SpringBootApplication
class Application(
    private val aircraftRepositories: AircraftRepositories,
    private val modelRepositories: ModelRepositories,
    private val pilotRepositories: PilotRepositories
) : CommandLineRunner {

    override fun run(vararg args: String?) {

        val modelA320 = Model(name = "Airbus A320")
        val modelBoeing737 = Model(name = "Boeing 737")

        modelRepositories.save(modelA320)
        modelRepositories.save(modelBoeing737)

        val aircraft101 = Aircraft(model = modelA320, flight = 101)
        val aircraft102 = Aircraft(model = modelA320, flight = 102)
        val aircraft201 = Aircraft(model = modelBoeing737, flight = 201)
        val aircraft202 = Aircraft(model = modelBoeing737, flight = 202)

        aircraftRepositories.saveAll(listOf(aircraft101, aircraft102, aircraft201, aircraft202))

        val pilot1 = Pilot(
            name = "Bob",
            aircraft = mutableListOf(
                aircraft101,
                aircraft102,
                aircraft201,
                aircraft202
            )
        )

        val pilot2 = Pilot(
            name = "Bill",
            aircraft = mutableListOf(
                aircraft201,
                aircraft202
            )
        )

        pilotRepositories.save(pilot1)
        pilotRepositories.save(pilot2)

        val found1 = pilot1.id?.let { pilotRepositories.findById(it) }
        println(found1)
        val found2 = pilotRepositories.findByName(pilot2.name)
        println(found2)

        val pilots = pilotRepositories.findAll()
        println("Пилоты: $pilots")

    }

}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
