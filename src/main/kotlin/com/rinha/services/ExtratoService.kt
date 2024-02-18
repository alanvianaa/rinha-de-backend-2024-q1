package com.rinha.services

import com.rinha.models.*

private val transacaoService = TransacaoService()
private val saldoService = SaldoService()

class ExtratoService {
    fun get(cliente: Cliente): Extrato {
        return Extrato(
            saldo = saldoService.get(cliente.id),
            ultimasTransacoes = transacaoService.ultimasTransacoes(cliente)
        )
    }
}
