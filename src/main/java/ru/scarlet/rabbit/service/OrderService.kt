package ru.scarlet.rabbit.service

import mu.KotlinLogging
import org.springframework.stereotype.Service
import ru.scarlet.rabbit.dto.OrderIn
import ru.scarlet.rabbit.event.Order
import ru.scarlet.rabbit.repository.OrderRepository
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

@Service
class OrderService(
    private val orderRepository: OrderRepository,
) {
    private val logger = KotlinLogging.logger { }

    fun getOrders() = orderRepository.findAll()

    fun saveOrder(order: OrderIn) {
        var orderOut: Order = Order(

        )
        orderOut.orderId = order.orderId
        orderOut.orderName = order.orderName
        orderOut.product = order.product
        orderOut.totalQuantity = order.totalQuantity
        orderOut.isSameDayDelivery = order.isSameDayDelivery
        orderOut.createdAt = LocalDateTime.ofInstant(Instant.ofEpochSecond(order.createdAt),
            ZoneId.of("Europe/Moscow"))

        orderRepository.save(orderOut)
    }

    fun getOrderById(id: UUID) = orderRepository.findById(id)
}