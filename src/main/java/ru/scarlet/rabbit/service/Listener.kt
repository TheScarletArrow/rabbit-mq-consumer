package ru.scarlet.rabbit.service

import mu.KotlinLogging
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service
import ru.scarlet.rabbit.configuration.COMMENT_QUEUE
import ru.scarlet.rabbit.configuration.POST_QUEUE
import ru.scarlet.rabbit.configuration.SESSION_QUEUE
import ru.scarlet.rabbit.configuration.customer_relation_queue
import ru.scarlet.rabbit.configuration.orderDispatchDepartment
import ru.scarlet.rabbit.dto.OrderIn
import ru.scarlet.rabbit.event.Comment
import ru.scarlet.rabbit.event.CurrentSession
import ru.scarlet.rabbit.event.Customer
import ru.scarlet.rabbit.event.Post
import ru.scarlet.rabbit.repository.CustomerRepository
import ru.scarlet.rabbit.repository.SessionRepository
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class Listener(
    val customerRepository: CustomerRepository,
    val sessionRepository: SessionRepository,
    val orderService: OrderService,
    val postService: PostService,
    val commentService: CommentService,
) {

    private val logger = KotlinLogging.logger { }

    @RabbitListener(queues = [customer_relation_queue])
    fun receiveNewCustomer(message: Customer) {

        logger.info(
            "Received Customer Details " +
                    "{}", message
        )
        //wait 10 ms
        Thread.sleep(10)
        customerRepository.save(message)
    }

    @RabbitListener(queues = [orderDispatchDepartment])
    fun receiveNewOrders(message: OrderIn) {
        logger.info(
            "Received Order " +
                    "{} with createdAt {}", message,
            LocalDateTime.ofEpochSecond(
                message.createdAt, 0, ZoneOffset.ofHours(3)
            )
        )
        Thread.sleep(10)

        orderService.saveOrder(message)
    }

//    @RabbitListener(queues = [STORE_QUEUE])
//    fun allStoreEvents(message: Any) {
//        logger.info(
//            "Receiving all events " +
//                    "{}", message
//        )
//    }

    @RabbitListener(queues = [SESSION_QUEUE])
    fun receiveNewSession(message: CurrentSession) {
        logger.info(
            "Received Session " +
                    "{} ", message,

        )
        Thread.sleep(10)

        sessionRepository.save(message)
    }

    @RabbitListener(queues = [POST_QUEUE])
    fun receiveNewPost(message: Post){
        logger.info { "Received Post $message" }
        Thread.sleep(10)

        postService.savePost(message)
    }

    @RabbitListener(queues = [COMMENT_QUEUE])
    fun receiveNewComment(message: Comment){
        logger.info { "Received Comment $message" }
        Thread.sleep(10)

        commentService.saveComment(message)
    }
}