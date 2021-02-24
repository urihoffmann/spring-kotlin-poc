package com.example.kotlinspringpoc.controller

import com.example.kotlinspringpoc.dao.mongo.MongoLimitDAO
import com.example.kotlinspringpoc.dao.postgres.PostgresLimitDAO
import com.example.kotlinspringpoc.dto.LimitDTO
import com.example.kotlinspringpoc.model.mongo.MongoLimit
import com.example.kotlinspringpoc.model.postgres.PostgresLimit
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/limits")
class LimitController (private val mongoLimitDAO: MongoLimitDAO, private val postgresLimitDAO: PostgresLimitDAO) {

    @GetMapping("/mongo")
    fun getMongoLimits(): MutableList<MongoLimit> = mongoLimitDAO.findAll()

    @GetMapping("/postgres")
    fun getPostgresLimits(): MutableList<PostgresLimit> = postgresLimitDAO.findAll()

    @GetMapping("/mongo/{cpf}")
    fun getMongoLimitByCpf (@PathVariable cpf:String) = mongoLimitDAO.findByCpf(cpf)

    @GetMapping("/postgres/{cpf}")
    fun getPostgresLimitByCpf (@PathVariable cpf:String) = postgresLimitDAO.findByCpf(cpf)

    @PostMapping("/mongo/first")
    fun setMongoFirstLimit(@RequestBody firstLimit: LimitDTO) {
        mongoLimitDAO.save(MongoLimit(firstLimit.cpf, firstLimit.value))
    }

    @PostMapping("/postgres/first")
    fun setPostgresFirstLimit(@RequestBody firstLimit: LimitDTO) {
        postgresLimitDAO.save(PostgresLimit(firstLimit.cpf, firstLimit.value))
    }


}