package com.rinha.models

import com.rinha.plugins.LocalDateTimeSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.LocalDateTime


@Serializable
data class Saldo(
    val total: Int,
    @Serializable(with = LocalDateTimeSerializer::class)
    @SerialName("data_extrato")
    val dataExtrato: LocalDateTime = LocalDateTime.now(),
    val limite: Int,
)

@Serializable
data class SaldoResposta(
    val saldo: Int,
    val limite: Int,
)