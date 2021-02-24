package com.example.kotlinspringpoc.dao.mongo

import com.example.kotlinspringpoc.model.mongo.MongoOrigination
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface MongoOriginationDAO: MongoRepository<MongoOrigination, UUID> {
    fun findAllByCpf(cpf: String): Collection<MongoOrigination>

}