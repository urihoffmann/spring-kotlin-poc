package com.example.kotlinspringpoc.service

import com.example.kotlinspringpoc.dto.OriginationDTO
import com.example.kotlinspringpoc.model.mongo.MongoLimit as MongoLimit
import com.example.kotlinspringpoc.model.mongo.MongoOrigination as MongoOrigination
import com.example.kotlinspringpoc.model.postgres.PostgresLimit as PostgresLimit
import com.example.kotlinspringpoc.model.postgres.PostgresOrigination as PostgresOrigination
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class OriginationService(
    private val mongoOriginationDAO: com.example.kotlinspringpoc.dao.mongo.MongoOriginationDAO,
    private val mongoLimitDAO: com.example.kotlinspringpoc.dao.mongo.MongoLimitDAO,
    private val postgresOriginationDAO: com.example.kotlinspringpoc.dao.postgres.PostgresOriginationDAO,
    private val postgresLimitDAO: com.example.kotlinspringpoc.dao.postgres.PostgresLimitDAO
) {

    fun registerOriginationMongo(originationDTO: OriginationDTO) {
        val limitDB = mongoLimitDAO.findByCpf(originationDTO.cpf)
        limitDB.orElseThrow { RuntimeException("Borrower with cpf ${originationDTO.cpf} does not have limit") }

        val newLimitValue = limitDB.get().value - originationDTO.value

        if (newLimitValue < 0) {
            throw RuntimeException("Borrower with cpf ${originationDTO.cpf} does not have sufficient limit")
        } else {
            mongoLimitDAO.save(MongoLimit(originationDTO.cpf, newLimitValue))
//            throw RuntimeException("fail")
            mongoOriginationDAO.save(MongoOrigination(originationDTO.cpf, originationDTO.value))
        }
    }

    fun registerOriginationPostgres(originationDTO: OriginationDTO) {
        val limitDB = postgresLimitDAO.findByCpf(originationDTO.cpf)
        limitDB.orElseThrow { RuntimeException("Borrower with cpf ${originationDTO.cpf} does not have limit") }

        val newLimitValue = limitDB.get().value - originationDTO.value

        if (newLimitValue < 0) {
            throw RuntimeException("Borrower with cpf ${originationDTO.cpf} does not have sufficient limit")
        } else {
            postgresLimitDAO.save(PostgresLimit(originationDTO.cpf, newLimitValue))
//            throw RuntimeException("fail")
            postgresOriginationDAO.save(
                PostgresOrigination(
                    UUID.randomUUID(),
                    originationDTO.cpf,
                    originationDTO.value,
                    Date(), null
                )
            )
        }
    }
}