package com.example.kotlinspringpoc.dao.mongo

import com.example.kotlinspringpoc.model.mongo.MongoLimit
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface MongoLimitDAO : MongoRepository<MongoLimit, String> {
    fun findByCpf(cpf: String): Optional<MongoLimit>
}