package com.example.kotlinspringpoc.dao.mongo

import com.example.kotlinspringpoc.model.mongo.Payment
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface PaymentDAO: MongoRepository<Payment, ObjectId> {
    fun findAllByCpf(cpf: String): Collection<Payment>
}