package ru.scarlet.rabbit.dto

import com.fasterxml.jackson.annotation.JsonProperty
import ru.scarlet.rabbit.event.Product
import java.time.Instant
import java.util.*

open class OrderIn {
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
    var createdAt: Long = Instant.now().epochSecond
    override fun toString(): String {
        return "Order(orderId=$orderId, orderName='$orderName', product=$product, totalQuantity=$totalQuantity, isSameDayDelivery=$isSameDayDelivery, createdAt=$createdAt)"
    }

}