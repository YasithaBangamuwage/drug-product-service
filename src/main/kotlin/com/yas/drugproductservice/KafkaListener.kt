package com.yas.drugproductservice

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class KafkaListener {

    @KafkaListener(topics = ["\${kafka.topic.name}"], groupId = "groupId")
    fun listener(data: String) {

        println("Listener received $data")
    }
}