package brz.marketplace.adapters.inbound.rest

import brz.marketplace.adapters.inbound.rest.UserController.Companion.API_USERS
import brz.marketplace.adapters.inbound.rest.model.UserResponse
import brz.marketplace.adapters.inbound.rest.model.UsersResponse
import brz.marketplace.common.annotations.InternalServerErrorApiResponse
import brz.marketplace.common.annotations.OkApiResponse
import brz.marketplace.ports.outbound.users.UserRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// TODO - Add authentication/authorization
@RestController
@RequestMapping(API_USERS)
private class UserController(private val userRepository: UserRepository) : Documented {
    companion object {
        const val API_USERS: String = "/api/v1/users"
    }

    @GetMapping
    override fun getUsers(): UsersResponse {
        return UsersResponse(
            userRepository.findAll().stream()
                .map { user -> UserResponse(user.id, user.email) }
                .toList()
        )
    }
}

@Tag(name = "Users", description = "Operations related to user management")
private interface Documented {
    @Operation(summary = "List all users", description = "Returns a list containing all users in the system.")
    @OkApiResponse
    @InternalServerErrorApiResponse // TODO - fix response body for this error response
    fun getUsers(): UsersResponse
}