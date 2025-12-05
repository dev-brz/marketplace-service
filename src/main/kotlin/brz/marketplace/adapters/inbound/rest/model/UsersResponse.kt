package brz.marketplace.adapters.inbound.rest.model

import io.swagger.v3.oas.annotations.media.Schema

internal data class UsersResponse(
    @Schema(description = "List of users")
    val users: List<UserResponse>
)

internal data class UserResponse(
    @Schema(description = "User ID", example = "123")
    val id: String,
    @Schema(description = "User email address", example = "john.doe@example.com")
    val email: String
)