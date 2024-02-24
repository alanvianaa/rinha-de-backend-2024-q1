package com.rinha.routes

import com.rinha.models.SaldoResposta
import com.rinha.models.Transacao
import com.rinha.services.ClienteService
import com.rinha.services.ExtratoService
import com.rinha.services.SaldoService
import com.rinha.services.TransacaoService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route


private val transacaoService = TransacaoService()
private val extratoService = ExtratoService()
private val clienteService = ClienteService()
private val saldoService = SaldoService()

fun Route.clienteRoute() {
    route("clientes/{id}") {
        post("transacoes") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val cliente = clienteService.get(id)
            val transacao = call.receive<Transacao>()

            if (cliente != null) {
                transacaoService.add(cliente, transacao)
                try {
                    val saldo = saldoService.update(cliente, transacao).let { SaldoResposta(it.total, it.limite) }
                    call.respond(HttpStatusCode.OK, saldo)
                } catch (e: InstantiationError) {
                    call.respond(HttpStatusCode.UnprocessableEntity)
                }
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
        get("extrato") {
            val id = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
            val cliente = clienteService.get(id)
            if (cliente != null) {
                val extrato = extratoService.get(cliente)
                call.respond(HttpStatusCode.OK, extrato)
            } else {
                call.respond(HttpStatusCode.NotFound)
            }
        }
    }
}
