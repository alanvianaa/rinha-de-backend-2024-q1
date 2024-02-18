package com.rinha.plugins

import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object DatabaseConnection {
    fun init() {
        Database.connect(
            url = "jdbc:postgresql://localhost/rinha-database",
            user = "postgres",
            password = "admin",
            driver = "org.postgresql.Driver"
        )
    }
}
