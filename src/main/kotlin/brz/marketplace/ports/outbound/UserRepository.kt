package brz.marketplace.ports.outbound

import brz.marketplace.domain.model.User

interface UserRepository {
    fun findAll(): List<User>
}