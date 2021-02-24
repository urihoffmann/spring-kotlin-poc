package com.example.kotlinspringpoc.dao.postgres

import com.example.kotlinspringpoc.model.postgres.PostgresOrigination
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PostgresOriginationDAO: JpaRepository<PostgresOrigination, UUID> {
    fun findAllByCpf(cpf: String): Collection<PostgresOrigination>
}