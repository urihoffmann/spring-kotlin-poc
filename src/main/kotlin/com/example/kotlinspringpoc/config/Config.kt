package com.example.kotlinspringpoc.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.MongoDatabaseFactory
import org.springframework.data.mongodb.MongoTransactionManager

import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration


@Configuration
class Config : AbstractMongoClientConfiguration() {
    @Bean
    fun transactionManager(dbFactory: MongoDatabaseFactory?): MongoTransactionManager? {
        return MongoTransactionManager(dbFactory!!)
    }

    override fun getDatabaseName(): String {
        return "kotlin-poc"
    }
}