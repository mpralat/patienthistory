import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.core.mapping.MongoMappingContext

//package com.mpralat.patienthistory.config
//
//import org.springframework.boot.context.event.ApplicationReadyEvent
//import org.springframework.context.event.EventListener
//
//
//@EventListener(ApplicationReadyEvent::class)
//fun initIndicesAfterStartup() {
//    val indexOps = mongoTemplate.indexOps(DomainType::class.java)
//}

@Bean
fun springDataMongoMappingContext(): MongoMappingContext = MongoMappingContext()