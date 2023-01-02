package ru.scarlet.rabbit.event

import com.fasterxml.jackson.annotation.JsonProperty
import org.springframework.data.mongodb.core.mapping.Document
import java.util.*

@Document(collection = "customer")
class Customer() {
    @JsonProperty("customerId")
    val customerId: UUID = UUID.randomUUID()
    @JsonProperty("customerName")
    val customerName: String? = null
    @JsonProperty("address")
    val address: Address = Address(streetName = "Street ##", city = "City ##", isHomeAddress = true)
    @JsonProperty("email")
    val email: String = "email"
    override fun toString(): String {
        return "Customer(customerId=$customerId, customerName=$customerName, address=$address, email='$email')"
    }
}
data class Address(

    @JsonProperty("streetName") val streetName: String?,
    @JsonProperty("city") val city: String?,
    @JsonProperty("isHomeAddress") val isHomeAddress: Boolean?,
) {
    override fun toString(): String {
        return "Address(streetName=$streetName, city=$city, isHomeAddress=$isHomeAddress)"
    }
}
