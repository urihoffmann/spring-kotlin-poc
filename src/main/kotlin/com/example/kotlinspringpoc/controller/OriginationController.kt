package com.example.kotlinspringpoc.controller

import com.example.kotlinspringpoc.dto.OriginationDTO
import com.example.kotlinspringpoc.model.mongo.MongoOrigination as MongoOrigination
import com.example.kotlinspringpoc.model.postgres.PostgresOrigination as PostgresOrigination
import com.example.kotlinspringpoc.service.OriginationService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/originations")
class OriginationController(
    private val mongoOriginationsDAO: com.example.kotlinspringpoc.dao.mongo.MongoOriginationDAO,
    private val postgresOriginationsDAO: com.example.kotlinspringpoc.dao.postgres.PostgresOriginationDAO,
    private val originationService: OriginationService
) {

    @GetMapping("/mongo")
    fun getOriginationsMongo(): MutableList<MongoOrigination> = mongoOriginationsDAO.findAll()

    @GetMapping("/postgres")
    fun getOriginationsPostgres(): MutableList<PostgresOrigination> = postgresOriginationsDAO.findAll()

    @GetMapping("/mongo/{cpf}")
    fun getMongoOriginationsByCpf(@PathVariable cpf: String): Collection<MongoOrigination> {
        return mongoOriginationsDAO.findAllByCpf(cpf)
    }

    @PostMapping("/mongo/register")
    fun registerMongoOrigination(@RequestBody origination: OriginationDTO): ResponseEntity<OriginationDTO> {
        return try {
            originationService.registerOriginationMongo(origination)
            ResponseEntity.ok(origination)
        } catch (exception: RuntimeException) {
            println(exception.message)
            throw exception
        }

    }

    @GetMapping("/postgres/{cpf}")
    fun getPostgresOriginationsByCpf(@PathVariable cpf: String): Collection<PostgresOrigination> {
        return postgresOriginationsDAO.findAllByCpf(cpf)
    }

    @PostMapping("/postgres/register")
    fun registerPostgresOrigination(@RequestBody origination: OriginationDTO): ResponseEntity<OriginationDTO> {
        return try {
            originationService.registerOriginationPostgres(origination)
            ResponseEntity.ok(origination)
        } catch (exception: RuntimeException) {
            println(exception.message)
            throw exception
        }

    }
}