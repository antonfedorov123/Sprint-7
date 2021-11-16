package ru.sber.orm.entities

import org.hibernate.annotations.NaturalId
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Model (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = 0,

    var name: String
)