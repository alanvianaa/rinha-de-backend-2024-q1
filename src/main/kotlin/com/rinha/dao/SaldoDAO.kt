package com.rinha.dao

import com.rinha.models.Clientes
import com.rinha.models.Saldo
import com.rinha.models.Saldos
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import java.time.LocalDateTime

class SaldoDAO {
    fun add(idCliente: Int, valor: Int): Unit = transaction {
        Saldos.insert {
            it[Saldos.idCliente] = idCliente
            it[Saldos.valor] = valor
        }
    }

    fun update(idCliente: Int, valor: Int): Unit = transaction {
        Saldos.update({ Saldos.idCliente eq idCliente }) {
            it[Saldos.valor] = valor
        }
    }

    fun get(idCliente: Int): Saldo? = transaction {
        Saldos.innerJoin(Clientes).slice(Saldos.valor, Clientes.limite)
            .select(Saldos.idCliente eq idCliente).map {
                Saldo(
                    total = it[Saldos.valor],
                    dataExtrato = LocalDateTime.now(),
                    limite = it[Clientes.limite]
                )
            }.singleOrNull()
    }
}
