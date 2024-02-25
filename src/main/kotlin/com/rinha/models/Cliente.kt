package com.rinha.models

import org.jetbrains.exposed.sql.Table

object Clientes : Table() {
    val id = integer("id").autoIncrement()
    val nome = varchar("nome", length = 50)
    val limite = integer("limite")
    val saldo = integer("saldo")

    override val primaryKey = PrimaryKey(id)
}
