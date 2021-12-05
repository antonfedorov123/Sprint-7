package ru.sber.rdbms

import java.sql.DriverManager
import java.sql.SQLException

class TransferPessimisticLock {

    val connection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/db",
        "postgres",
        "postgres"
    )

    fun transfer(accountId1: Long, accountId2: Long, amount: Long) {
        connection.use { conn ->

            try {
                conn.autoCommit = false

                conn.prepareStatement("SELECT * FROM account1 WHERE id = ?").use { prepared ->
                    prepared.setLong(1, accountId1)
                    prepared.executeQuery().use { resultSet ->
                        resultSet.next()
                        if (resultSet.getLong("amount") - amount < 0) {
                            throw SQLException("Не хватает средств на счёте")
                        }
                    }
                }

                conn.prepareStatement("SELECT * FROM account1 WHERE id IN (?, ?) FOR UPDATE").use { prepared ->
                    prepared.setLong(1, accountId1)
                    prepared.setLong(2, accountId2)
                    prepared.executeQuery()
                }

                conn.prepareStatement("UPDATE account1 SET amount = amount - ? WHERE id = ?").use { prepared ->
                    prepared.setLong(1, amount)
                    prepared.setLong(2, accountId1)
                    prepared.executeUpdate()
                }

                conn.prepareStatement("UPDATE account1 SET amount = amount + ? WHERE id = ?").use { prepared ->
                    prepared.setLong(1, amount)
                    prepared.setLong(2, accountId2)
                    prepared.executeUpdate()
                }

                conn.commit()
            } catch (exception: Exception) {
                println(exception.message)
                conn.rollback()
            } finally {
                conn.autoCommit = true
            }
        }
    }
}
