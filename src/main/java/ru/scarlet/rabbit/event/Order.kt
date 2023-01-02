package ru.scarlet.rabbit.event

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.NoArgsConstructor
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import java.util.*


@NoArgsConstructor
@Document(collection = "order", language = "russian", collation = "{locale: 'ru', strength: 2}")
open class Order {
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
data class Product(
    @JsonProperty("name") val name: String,
    @JsonProperty("quantity") val quantity: Int

) {
    override fun toString(): String {
        return "Product(name='$name', quantity=$quantity)"
    }
}