package com.rinha.dao

import com.rinha.models.Transacao
import com.rinha.models.Transacoes
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

class TransacaoDAO {
    fun add(idCliente: Int, transacao: Transacao): Int = transaction {
        Transacoes.insert {
            it[Transacoes.idCliente] = idCliente
            it[valor] = transacao.valor
            it[tipo] = transacao.tipo
            it[descricao] = transacao.descricao
            it[realizadaEm] = transacao.realizadaEm
        }[Transacoes.id]
    }

    fun ultimasTransacoes(idCliente: Int): List<Transacao> = transaction {
        Transacoes.select(Transacoes.idCliente eq idCliente).map {
            Transacao(
                it[Transacoes.valor],
                it[Transacoes.tipo],
                it[Transacoes.descricao],
                it[Transacoes.realizadaEm],
            )
        }
    }
}