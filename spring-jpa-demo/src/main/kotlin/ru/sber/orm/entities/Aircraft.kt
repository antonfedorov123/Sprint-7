package ru.sber.orm.entities

import javax.persistence.*

@Entity
@Table(name="aircraft")
class Aircraft (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = 0,

    var flight: Int,

    @ManyToOne
    var model: Model
)