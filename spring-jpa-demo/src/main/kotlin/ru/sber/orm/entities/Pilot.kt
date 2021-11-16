package ru.sber.orm.entities

import org.hibernate.annotations.NaturalId
import javax.persistence.*

@Entity
@Table(name="pilot")
data class Pilot(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = 0,

    @NaturalId
    var name: String,

    @ManyToMany(fetch = FetchType.EAGER)
    var aircraft: MutableList<Aircraft>
)