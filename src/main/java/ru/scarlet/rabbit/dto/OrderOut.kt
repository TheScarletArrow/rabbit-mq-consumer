package ru.scarlet.rabbit.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.AllArgsConstructor
import ru.scarlet.rabbit.event.Product
import java.time.Instant
import java.time.LocalDateTime
import java.util.*

@AllArgsConstructor
open class OrderOut {
    @JsonProperty("orderId")
    var orderId: UUID = UUID.randomUUID()
    @JsonProperty("orderName")
    var orderName: String = "Order ##"
    @JsonProperty("product")
    var product: List<Product>? = listOf(
        Product(
            name = "Product ##",
            quantity = (1..10).random()
        )
    )
    @JsonProperty("totalQuantity")
    var totalQuantity: Int = (1..10).random()
    @JsonProperty("isSameDayDelivery")
    var isSameDayDelivery: Boolean = (0..1).random() == 1
    @JsonProperty("createdAt")
    var createdAt: LocalDateTime = LocalDateTime.now()
    override fun toString(): String {
        return "Order(orderId=$orderId, orderName='$orderName', product=$product, totalQuantity=$totalQuantity, isSameDayDelivery=$isSameDayDelivery, createdAt=$createdAt)"
    }

}