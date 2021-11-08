package ru.sber.orm

import org.hibernate.cfg.Configuration
import ru.sber.orm.dao.AircraftDao
import ru.sber.orm.dao.ModelDao
import ru.sber.orm.dao.PilotsDao
import ru.sber.orm.entities.Aircraft
import ru.sber.orm.entities.Model
import ru.sber.orm.entities.Pilot

fun main(args: Array<String>) {

    val sessionFactory = Configuration().configure()
        .addAnnotatedClass(Model::class.java)
        .addAnnotatedClass(Aircraft::class.java)
        .addAnnotatedClass(Pilot::class.java)
        .buildSessionFactory()

//    sessionFactory.use {
//
//        val pilotsDao = PilotsDao(sessionFactory)
//        val modelDao = ModelDao(sessionFactory)
//        val aircraftDao = AircraftDao(sessionFactory)
//
//        val modelA320 = Model(name = "Airbus A320")
//        val modelBoeing737 = Model(name = "Boeing 737")
//
//        modelDao.save(modelA320)
//        modelDao.save(modelBoeing737)
//
//        val aircraft101 = Aircraft(model = modelA320, flight = 101)
//        val aircraft102 = Aircraft(model = modelA320, flight = 102)
//        val aircraft201 = Aircraft(model = modelBoeing737, flight = 201)
//        val aircraft202 = Aircraft(model = modelBoeing737, flight = 202)
//
//        aircraftDao.save(aircraft101)
//        aircraftDao.save(aircraft102)
//        aircraftDao.save(aircraft201)
//        aircraftDao.save(aircraft202)
//
//        val pilot1 = Pilot(
//            name = "Bob",
//            plains = mutableListOf(
//                aircraft101,
//                aircraft102,
//                aircraft201,
//                aircraft202
//            )
//        )
//
//        val pilot2 = Pilot(
//            name = "Bill",
//            plains = mutableListOf(
//                aircraft201,
//                aircraft202
//            )
//        )
//
//        pilotsDao.save(pilot1)
//        pilotsDao.save(pilot2)
//
//        val found1 = pilotsDao.find(pilot1.id)
//        println(found1)
//        val found2 = pilotsDao.find(pilot2.name)
//        println(found2)
//
//        val pilots = pilotsDao.findAll()
//        println("Пилоты: $pilots")
//    }

}