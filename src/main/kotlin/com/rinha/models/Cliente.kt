package com.rinha.models

import kotlinx.serialization.Serializable

import org.jetbrains.exposed.sql.Table

object Clientes : Table() {
    val id = integer("id").autoIncrement()
    val nome = varchar("nome", length = 50)
    val limite = integer("limite")

    override val primaryKey = PrimaryKey(id)
}

@Serializable
data class Cliente(
    val id: Int,
    val nome: String,
    val limite: Int
)

