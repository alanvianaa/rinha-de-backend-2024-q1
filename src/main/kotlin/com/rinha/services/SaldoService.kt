package com.rinha.services

import com.rinha.dao.SaldoDAO
import com.rinha.models.Cliente
import com.rinha.models.Saldo
import com.rinha.models.Transacao

private val saldoDAO = SaldoDAO()

class SaldoService {

    fun get(idCliente: Int): Saldo {
        val saldo = saldoDAO.get(idCliente)
        if (saldo == null) saldoDAO.add(idCliente, 0)
        return saldo ?: get(idCliente)
    }

    fun update(cliente: Cliente, transacao: Transacao): Saldo {
        val ultimoSaldo = get(cliente.id)

        val valor = if (transacao.tipo == "d") {
            ultimoSaldo.total - transacao.valor
        } else {
            ultimoSaldo.total + transacao.valor
        }

        if (valor < ultimoSaldo.limite * -1) throw InstantiationError("Invalid ID")

        saldoDAO.update(cliente.id, valor)

        return get(cliente.id)
    }
}