package com.rinha.routes

import com.rinha.models.Transacao
import com.rinha.services.ExtratoService
import com.rinha.services.TransacaoService
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.plugins.NotFoundException
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.get
import io.ktor.server.routing.post
import io.ktor.server.routing.route


private val transacaoService = TransacaoService()
private val extratoService = ExtratoService()

fun Route.clienteRoute() {
    route("clientes/{id}") {
        post("transacoes") {
            try {
                val idCliente = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
                val saldo = transacaoService.add(idCliente, call.receive<Transacao>())
                call.respond(HttpStatusCode.OK, saldo)
            } catch (e: InstantiationError) {
                call.respond(HttpStatusCode.UnprocessableEntity)
            } catch (e: NotFoundException) {
                call.respond(HttpStatusCode.NotFound)
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.UnprocessableEntity)
            }
        }
        get("extrato") {
            try {
                val idCliente = call.parameters["id"]?.toInt() ?: throw IllegalArgumentException("Invalid ID")
                val extrato = extratoService.get(idCliente)
                call.respond(HttpStatusCode.OK, extrato)
            } catch (e: NotFoundException) {
                call.respond(HttpStatusCode.NotFound)
            } catch (e: IllegalArgumentException) {
                call.respond(HttpStatusCode.UnprocessableEntity)
            }
        }
    }
}
