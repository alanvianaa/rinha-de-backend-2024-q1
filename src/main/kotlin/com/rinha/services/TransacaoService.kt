package com.rinha.services

import com.rinha.models.Clientes
import com.rinha.models.SaldoResposta
import com.rinha.models.Transacao
import com.rinha.models.Transacoes
import io.ktor.server.plugins.NotFoundException
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update

class TransacaoService {

    fun add(idCliente: Int, transacao: Transacao): SaldoResposta = transaction {

        val (ultimoSaldo, limite) = Clientes.slice(Clientes.saldo, Clientes.limite)
            .select(Clientes.id eq idCliente).forUpdate().map {
                it[Clientes.limite] to it[Clientes.limite]
            }.singleOrNull() ?: throw NotFoundException("Cliente nao encontrado")

        val novoSaldo = when (transacao.tipo) {
            "d" -> ultimoSaldo - transacao.valor
            "c" -> ultimoSaldo + transacao.valor
            else -> throw InstantiationError("Operacao incorreta")
        }

        if (novoSaldo < limite * -1) throw InstantiationError("Nao processado")

        Transacoes.insert {
            it[Transacoes.idCliente] = idCliente
            it[valor] = transacao.valor
            it[tipo] = transacao.tipo
            it[descricao] = transacao.descricao
            it[realizadaEm] = transacao.realizadaEm
        }[Transacoes.id]

        Clientes.update({ Clientes.id eq idCliente }) {
            it[saldo] = novoSaldo
        }

        SaldoResposta(
            saldo = novoSaldo,
            limite = limite
        )
    }
}
