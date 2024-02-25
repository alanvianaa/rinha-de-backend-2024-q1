package com.rinha.services

import com.rinha.models.Clientes
import com.rinha.models.Extrato
import com.rinha.models.Saldo
import com.rinha.models.Transacao
import com.rinha.models.Transacoes
import io.ktor.server.plugins.NotFoundException
import org.jetbrains.exposed.sql.SortOrder
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction


class ExtratoService {
    fun get(idCliente: Int): Extrato = transaction {

        val (saldos, transacoes) = Clientes.leftJoin(Transacoes).slice(
            Clientes.saldo,
            Clientes.limite,
            Transacoes.valor,
            Transacoes.tipo,
            Transacoes.descricao,
            Transacoes.realizadaEm
        ).select { Clientes.id eq idCliente }
            .orderBy(Transacoes.realizadaEm, SortOrder.DESC)
            .limit(10)
            .mapNotNull {
                Saldo(
                    total = it[Clientes.saldo],
                    limite = it[Clientes.limite]
                ) to if (it[Transacoes.valor] != null) Transacao(
                    valor = it[Transacoes.valor],
                    tipo = it[Transacoes.tipo],
                    descricao = it[Transacoes.descricao],
                    realizadaEm = it[Transacoes.realizadaEm]
                ) else null
            }.unzip()

        Extrato(
            saldo = saldos.firstOrNull() ?: throw NotFoundException("Cliente nao encontrado"),
            ultimasTransacoes = transacoes.mapNotNull { it }
        )
    }
}
