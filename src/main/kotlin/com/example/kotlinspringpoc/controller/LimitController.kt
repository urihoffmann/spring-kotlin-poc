package com.example.kotlinspringpoc.controller

import com.example.kotlinspringpoc.dao.mongo.LimitDAO
import com.example.kotlinspringpoc.model.mongo.Limit
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/limits")
class LimitController (private val limitDAO: LimitDAO) {
    @GetMapping
    fun getLimits(): MutableList<Limit> = limitDAO.findAll()

    @GetMapping("/{cpf}")
    fun getLimitByCpf (@PathVariable cpf:String) = limitDAO.findByCpf(cpf)

    @PostMapping("/first")
    fun setFirstLimit(@RequestBody firstLimit: Limit) {
        limitDAO.save(firstLimit)
    }

}