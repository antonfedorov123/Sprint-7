package ru.sber.orm.entities

import org.hibernate.annotations.NaturalId
import javax.persistence.*

@Entity
@Table(name="pilot")
class Pilot(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = 0,

    @NaturalId
    var name: String,

    @ManyToMany(cascade = [CascadeType.PERSIST], fetch = FetchType.EAGER)
    var plains: MutableList<Aircraft> = ArrayList()
)