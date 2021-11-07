package ru.sber.orm.dao

import org.hibernate.SessionFactory
import ru.sber.orm.entities.Aircraft

class AircraftDao(private val sessionFactory: SessionFactory) {

    fun save(pilot: Aircraft) {
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            session.save(pilot)
            session.transaction.commit()
        }
    }

    fun find(id: Long?): Aircraft? {
        val result: Aircraft?
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            result = session.get(Aircraft::class.java, id)
            session.transaction.commit()
        }
        return result
    }
}