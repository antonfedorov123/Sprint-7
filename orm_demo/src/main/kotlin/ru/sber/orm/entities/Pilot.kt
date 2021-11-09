package ru.sber.orm.entities

import org.hibernate.annotations.NaturalId
import javax.persistence.*

@Entity
data class Pilot(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = 0,

    @NaturalId
    var name: String,

    @ManyToMany
    var aircraft: MutableList<Aircraft> = ArrayList()
)