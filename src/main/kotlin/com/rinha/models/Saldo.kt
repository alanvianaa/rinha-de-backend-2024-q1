package com.rinha.models

import com.rinha.plugins.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import java.time.LocalDateTime

object Saldos : Table() {
    val id = integer("id").autoIncrement()
    val idCliente = integer("id_cliente").references(Clientes.id, onUpdate = ReferenceOption.CASCADE)
    val valor = integer("valor")

    override val primaryKey = PrimaryKey(id)
}

@Serializable
data class Saldo(
    val total: Int,
    @Serializable(with = LocalDateTimeSerializer::class)
    @SerialName("data_extrato")
    val dataExtrato: LocalDateTime,
    val limite: Int,
)

@Serializable
data class SaldoResposta(
    val saldo: Int,
    val limite: Int,
)