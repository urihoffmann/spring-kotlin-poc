package com.example.kotlinspringpoc.dao.postgres

import com.example.kotlinspringpoc.model.postgres.PostgresLimit
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PostgresLimitDAO: JpaRepository<PostgresLimit, String> {
    fun findByCpf(cpf: String): Optional<PostgresLimit>
}