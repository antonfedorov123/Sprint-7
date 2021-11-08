package ru.sber.orm.entities

import javax.persistence.*

@Entity
@Table(name="model")
class Model (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = 0,

    var name: String
)