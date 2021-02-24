package com.example.kotlinspringpoc.dao.mongo

import com.example.kotlinspringpoc.model.mongo.MongoPayment
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface MongoPaymentDAO: MongoRepository<MongoPayment, UUID> {
    fun findAllByCpf(cpf: String): Collection<MongoPayment>
}