package brz.marketplace.adapters.outbound.users

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties("keycloak")
internal data class KeycloakConfigProperties(
    val url: String,
    val realm: String,
    val admin: Admin,
    val client: Client,
)

internal data class Client(val secret: String, val id: String)

internal data class Admin(val username: String, val password: String)