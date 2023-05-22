package com.yas.drugproductservice.events

import org.apache.kafka.clients.admin.NewTopic
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.config.TopicBuilder

@Configuration
class KafkaTopic {

    @Value("\${kafka.topic.name}")
    private lateinit var topicName: String

    @Bean
    fun drugProductServiceTopic(): NewTopic = TopicBuilder.name(topicName).build()
}