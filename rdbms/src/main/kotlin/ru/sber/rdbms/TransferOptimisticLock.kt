package ru.sber.rdbms

import java.sql.DriverManager
import java.sql.SQLException

class TransferOptimisticLock {
    private val connection = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/db",
        "postgres",
        "postgres"
    )

    fun transfer(accountId1: Long, accountId2: Long, amount: Long) {
        var oldVersion: Long
        val autoCommit = connection.autoCommit

        connection.use { conn ->
            try {
                conn.autoCommit = false
                conn.prepareStatement("SELECT * FROM account1 WHERE id = ?").use { prepared ->
                    prepared.setLong(1, accountId1)
                    prepared.executeQuery().use { resultSet ->
                        resultSet.next()
                        if (resultSet.getLong("amount") - amount < 0)
                            throw SQLException("Не хватает средств на счёте")
                        oldVersion = resultSet.getLong("version")
                    }
                }

                conn.prepareStatement("UPDATE account1 SET amount = amount - ?, version = version + 1 WHERE id = ? and version = ? ").use { prepared ->
                    prepared.setLong(1, amount)
                    prepared.setLong(2, accountId1)
                    prepared.setLong(3, oldVersion)
                    val result = prepared.executeUpdate()
                    if (result == 0)
                        throw SQLException("Ошибка при обновлении данных")
                }

                conn.prepareStatement("SELECT * FROM account1 WHERE id = ?").use { prepared ->
                    prepared.setLong(1, accountId2)
                    prepared.executeQuery().use { resultSet ->
                        resultSet.next()
                        oldVersion = resultSet.getLong("version")
                    }
                }

                conn.prepareStatement("UPDATE account1 SET amount = amount + ?, version = version + 1 WHERE id = ? and version = ?").use { prepared ->
                    prepared.setLong(1, amount)
                    prepared.setLong(2, accountId2)
                    prepared.setLong(3, oldVersion)
                    val result = prepared.executeUpdate()
                    if (result == 0) {
                        throw SQLException("Ошибка при обновлении данных")
                    }
                }

                conn.commit()
            } catch (exception: Exception){
                println(exception.message)
                conn.rollback()
            } finally {
                conn.autoCommit = autoCommit
            }
        }
    }
}
