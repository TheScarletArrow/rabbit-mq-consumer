package ru.scarlet.rabbit.repository

import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import ru.scarlet.rabbit.event.Order
import java.util.UUID

@Repository
interface OrderRepository : MongoRepository<Order, UUID> {
}