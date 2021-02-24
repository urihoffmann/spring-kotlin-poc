package com.example.kotlinspringpoc.service

import com.example.kotlinspringpoc.dto.PaymentDTO
import com.example.kotlinspringpoc.model.mongo.MongoLimit as MongoLimit
import com.example.kotlinspringpoc.model.mongo.MongoPayment as MongoPayment
import com.example.kotlinspringpoc.model.postgres.PostgresLimit as PostgresLimit
import com.example.kotlinspringpoc.model.postgres.PostgresPayment as PostgresPayment
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service

class PaymentService(
    private val mongoPaymentDAO: com.example.kotlinspringpoc.dao.mongo.MongoPaymentDAO,
    private val mongoOriginationDAO: com.example.kotlinspringpoc.dao.mongo.MongoOriginationDAO,
    private val mongoLimitDAO: com.example.kotlinspringpoc.dao.mongo.MongoLimitDAO,
    private val postgresPaymentDAO: com.example.kotlinspringpoc.dao.postgres.PostgresPaymentDAO,
    private val postgresOriginationDAO: com.example.kotlinspringpoc.dao.postgres.PostgresOriginationDAO,
    private val postgresLimitDAO: com.example.kotlinspringpoc.dao.postgres.PostgresLimitDAO
) {
    @Transactional
    fun registerPaymentMongo(paymentDTO: PaymentDTO) {
        val originationDB = mongoOriginationDAO.findById(paymentDTO.originationId)
        originationDB.orElseThrow { RuntimeException("There is no Origination with id ${paymentDTO.originationId}. Unable to process Payment ") }

        val originationValue = originationDB.get().value

        if (originationValue != paymentDTO.value) {
            throw RuntimeException(" The payment value does not match with the origination Value!. Unable to process Payment")
        } else {
            val limitDB = mongoLimitDAO.findByCpf(paymentDTO.cpf)
            val newLimitValue = limitDB.get().value + paymentDTO.value

            mongoLimitDAO.save(MongoLimit(paymentDTO.cpf, newLimitValue))
            mongoPaymentDAO.save(
                MongoPayment(paymentDTO.cpf, paymentDTO.value, paymentDTO.originationId)
            )
        }

    }

    @Transactional
    fun registerPaymentPostgres(paymentDTO: PaymentDTO) {
        val originationDB = postgresOriginationDAO.findById(paymentDTO.originationId)
        originationDB.orElseThrow { RuntimeException("There is no Origination with id ${paymentDTO.originationId}. Unable to process Payment ") }
        val origination = originationDB.get()
        val originationValue = origination.value

        if (originationValue != paymentDTO.value) {
            throw RuntimeException(" The payment value does not match with the origination Value!. Unable to process Payment")
        } else {
            val limitDB = postgresLimitDAO.findByCpf(paymentDTO.cpf)
            val newLimitValue = limitDB.get().value + paymentDTO.value

            postgresLimitDAO.save(PostgresLimit(paymentDTO.cpf, newLimitValue)  )
            postgresPaymentDAO.save(PostgresPayment(UUID.randomUUID(), paymentDTO.cpf, paymentDTO.value, Date(), origination))
        }

    }
}