package ru.sber.orm.entities

import javax.persistence.*

@Entity
class Aircraft (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = 0,

    var flight: Int,

    @OneToOne(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
    var model: Model
)