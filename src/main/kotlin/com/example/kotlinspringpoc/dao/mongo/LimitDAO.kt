package com.example.kotlinspringpoc.dao.mongo

import com.example.kotlinspringpoc.model.mongo.Limit
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface LimitDAO : MongoRepository<Limit, String> {
    fun findByCpf(cpf: String): Optional<Limit>
}