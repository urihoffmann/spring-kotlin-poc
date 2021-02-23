package com.example.kotlinspringpoc.controller

import com.example.kotlinspringpoc.dao.mongo.OriginationDAO
import com.example.kotlinspringpoc.dto.OriginationDTO
import com.example.kotlinspringpoc.model.mongo.Limit
import com.example.kotlinspringpoc.model.mongo.Origination
import com.example.kotlinspringpoc.service.OriginationService
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.*
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/originations")
class OriginationController(
    private val originationsDAO: OriginationDAO,
    private val originationService: OriginationService
) {

    @GetMapping
    fun getOriginations(): MutableList<Origination> = originationsDAO.findAll()

    @GetMapping("/{cpf}")
    fun getOriginationsByCpf(@PathVariable cpf: String): Collection<Origination> {
        return originationsDAO.findAllByCpf(cpf)
    }

    @PostMapping("/register")
    fun registerOrigination(@RequestBody origination: OriginationDTO): ResponseEntity<OriginationDTO> {
        return try {
            originationService.registerOrigination(origination)
            ResponseEntity.ok(origination)
        } catch (exception: RuntimeException) {
            println(exception.message)
            throw exception
        }

    }
}