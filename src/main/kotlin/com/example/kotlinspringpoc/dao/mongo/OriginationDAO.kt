package com.example.kotlinspringpoc.dao.mongo

import com.example.kotlinspringpoc.model.mongo.Origination
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import java.util.*

interface OriginationDAO: MongoRepository<Origination, ObjectId> {
    fun findAllByCpf(cpf: String): Collection<Origination>

    fun findById(id: UUID): Optional<Origination>
}