package com.rinha.models

import com.rinha.plugins.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime


object Transacoes : Table() {
    val id = integer("id").autoIncrement()
    val idCliente = integer("id_cliente").references(Clientes.id, onUpdate = ReferenceOption.CASCADE)
    val valor = integer("valor")
    val tipo = varchar("tipo", length = 1)
    val descricao = varchar("descricao", length = 10)
    val realizadaEm = datetime("realizada_em")

    override val primaryKey = PrimaryKey(id)
}

@Serializable
data class Transacao(
    val valor: Int,
    val tipo: String,
    val descricao: String,
    @Serializable(with = LocalDateTimeSerializer::class)
    @SerialName("realizada_em")
    val realizadaEm: LocalDateTime = LocalDateTime.now()
)