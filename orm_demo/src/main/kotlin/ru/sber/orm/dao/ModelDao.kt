package ru.sber.orm.dao

import org.hibernate.SessionFactory
import ru.sber.orm.entities.Model

class ModelDao(private val sessionFactory: SessionFactory) {

    fun save(pilot: Model) {
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            session.save(pilot)
            session.transaction.commit()
        }
    }

    fun find(id: Long?): Model? {
        val result: Model?
        sessionFactory.openSession().use { session ->
            session.beginTransaction()
            result = session.get(Model::class.java, id)
            session.transaction.commit()
        }
        return result
    }

}