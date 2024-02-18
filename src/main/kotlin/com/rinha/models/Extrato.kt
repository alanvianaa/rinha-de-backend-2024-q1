package com.rinha.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Extrato(
    val saldo: Saldo,
    @SerialName("ultimas_transacoes")
    val ultimasTransacoes: List<Transacao>
)
