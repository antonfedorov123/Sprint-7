package ru.sber.rdbms

import java.sql.DriverManager
import java.sql.SQLException

class TransferConstraint {

    private val connection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/db",
        "postgres",
        "postgres"
    )

    fun transfer(accountId1: Long, accountId2: Long, amount: Long) {

        connection.use { connection ->
            try {
                connection.prepareStatement("UPDATE account1 SET amount = amount - ? WHERE id = ?").use { prepared ->
                    prepared.setLong(1, amount)
                    prepared.setLong(2, accountId1)
                    prepared.executeUpdate()
                }

                connection.prepareStatement("UPDATE account1 SET amount = amount + ? WHERE id = ?").use { prepared ->
                    prepared.setLong(1, amount)
                    prepared.setLong(2, accountId2)
                    prepared.executeUpdate()
                }

            } catch (exception: SQLException) {
                println(exception.message)
            }
        }
    }
}