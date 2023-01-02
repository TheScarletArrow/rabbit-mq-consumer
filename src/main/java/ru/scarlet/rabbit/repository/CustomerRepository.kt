package ru.scarlet.rabbit.repository

import org.springframework.data.mongodb.repository.MongoRepository
import ru.scarlet.rabbit.event.Customer
import java.util.UUID

interface CustomerRepository : MongoRepository<Customer, UUID> {
}
