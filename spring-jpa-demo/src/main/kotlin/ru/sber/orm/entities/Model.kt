package ru.sber.orm.entities

import org.hibernate.annotations.NaturalId
import javax.persistence.*

@Entity
@Table(name="model")
class Model (
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = 0,

    var name: String
)