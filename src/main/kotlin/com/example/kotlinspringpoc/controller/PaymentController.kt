package com.example.kotlinspringpoc.controller

import com.example.kotlinspringpoc.dao.mongo.MongoPaymentDAO
import com.example.kotlinspringpoc.dao.postgres.PostgresPaymentDAO
import com.example.kotlinspringpoc.dto.PaymentDTO
import com.example.kotlinspringpoc.model.mongo.MongoPayment
import com.example.kotlinspringpoc.model.postgres.PostgresPayment
import com.example.kotlinspringpoc.service.PaymentService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/payments")

class PaymentController(private val mongoPaymentsDAO: MongoPaymentDAO, private val postgresPaymentDAO: PostgresPaymentDAO, private val paymentService: PaymentService) {

    @GetMapping("/mongo")
    fun getMongoPayments(): MutableList<MongoPayment> = mongoPaymentsDAO.findAll()

    @GetMapping("/postgres")
    fun getPostgresPayments(): MutableList<PostgresPayment> = postgresPaymentDAO.findAll()

    @GetMapping("/mongo/{cpf}")
    fun getMongoPaymentsByCpf(@PathVariable cpf: String): Collection<MongoPayment> {
        return mongoPaymentsDAO.findAllByCpf(cpf)
    }

    @GetMapping("/postgres/{cpf}")
    fun getPostgresPaymentsByCpf(@PathVariable cpf: String): Collection<PostgresPayment> {
        return postgresPaymentDAO.findAllByCpf(cpf)
    }

    @PostMapping("/mongo/register")
    fun registerMongoOrigination(@RequestBody payment: PaymentDTO): ResponseEntity<PaymentDTO> {
        return try {
            paymentService.registerPaymentMongo(payment)
            ResponseEntity.ok(payment)
        } catch (exception: RuntimeException) {
            println(exception.message)
            throw exception
        }
    }

    @PostMapping("/postgres/register")
    fun registerPostgresOrigination(@RequestBody payment: PaymentDTO): ResponseEntity<PaymentDTO> {
        return try {
            paymentService.registerPaymentPostgres(payment)
            ResponseEntity.ok(payment)
        } catch (exception: RuntimeException) {
            println(exception.message)
            throw exception
        }
    }


}