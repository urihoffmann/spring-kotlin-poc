package com.example.kotlinspringpoc.dao.postgres

import com.example.kotlinspringpoc.model.postgres.PostgresPayment
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PostgresPaymentDAO: JpaRepository<PostgresPayment, UUID> {
   fun findAllByCpf(cpf: String): Collection<PostgresPayment>
}