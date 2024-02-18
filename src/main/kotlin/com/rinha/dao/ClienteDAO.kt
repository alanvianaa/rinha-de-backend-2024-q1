package com.rinha.dao

import com.rinha.models.Cliente
import com.rinha.models.Clientes
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction

class ClienteDAO {
    fun get(id: Int): Cliente? = transaction {
        Clientes.select { Clientes.id eq id }.map { Cliente(it[Clientes.id], it[Clientes.nome], it[Clientes.limite]) }
            .singleOrNull()
    }
}