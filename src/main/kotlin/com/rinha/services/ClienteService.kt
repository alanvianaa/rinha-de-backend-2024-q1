package com.rinha.services

import com.rinha.dao.ClienteDAO

class ClienteService {
    fun get(id: Int) = ClienteDAO().get(id)
}