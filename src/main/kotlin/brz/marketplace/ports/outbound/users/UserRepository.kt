package brz.marketplace.ports.outbound.users

import brz.marketplace.domain.model.User

interface UserRepository {
    /**
     * Find all Marketplace users.
     *
     * @return users list (might be empty in case no users are present)
     */
    fun findAll(): List<User>
}