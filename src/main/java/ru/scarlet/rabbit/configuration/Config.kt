package ru.scarlet.rabbit.configuration

import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

const val EXCHANGE_NAME = "storeExchange"

const val customer_relation_queue = "customerRelationsDepartment"
const val orderDispatchDepartment = "orderDispatchDepartment"
const val STORE_QUEUE = "allEvents"
const val SESSION_QUEUE = "sessionEvents"
const val POST_QUEUE = "postEvents"
const val COMMENT_QUEUE = "commentEvents"

@Configuration
class Config {


@Bean
fun rabbitTemplate(
        connectionFactory: ConnectionFactory,
        jackson2MessageConverter: Jackson2JsonMessageConverter
): RabbitTemplate {
    val rabbitTemplate = RabbitTemplate(connectionFactory)
    rabbitTemplate.messageConverter = jackson2MessageConverter
    return rabbitTemplate
}

    @Bean
    fun jackson2MessageConverter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }
}