package com.rinha.services

import com.rinha.dao.TransacaoDAO
import com.rinha.models.Cliente
import com.rinha.models.Transacao

class TransacaoService {

    val transacaoDAO = TransacaoDAO()

    fun add(cliente: Cliente, transacao: Transacao) {
        transacaoDAO.add(cliente.id, transacao)
    }

    fun ultimasTransacoes(cliente: Cliente): List<Transacao> {
        return transacaoDAO.ultimasTransacoes(cliente.id)
    }
}