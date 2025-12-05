package brz.marketplace.adapters.outbound

import brz.marketplace.common.utils.TypeUtils.Companion.typeReference
import brz.marketplace.domain.model.User
import brz.marketplace.ports.outbound.UserRepository
import org.springframework.http.MediaType
import org.springframework.stereotype.Repository
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestClient

@Repository
private class KeycloakUserRepository(
    private val keycloakProperties: KeycloakConfigProperties
) : UserRepository {
    override fun findAll(): List<User> {
        // Create WebClient
        val webClient = RestClient.builder()
            .baseUrl("${keycloakProperties.url}/admin/realms/${keycloakProperties.realm}")
            .defaultHeader("Authorization", "Bearer " + getAccessToken())
            .build()

        // Fetch user list
        return webClient.get()
            .uri("/users")
            .retrieve()
            .body(typeReference<List<User>>())!!
    }

    private fun getAccessToken(): String {
        // Create WebClient
        val webClient = RestClient.builder()
            .baseUrl("${keycloakProperties.url}/realms/master/protocol/openid-connect/token")
            .build()

        // Build request body
        val requestBody = LinkedMultiValueMap<String, String>()
        requestBody.add("grant_type", "password")
        requestBody.add("client_id", "admin-cli")
        requestBody.add("username", keycloakProperties.admin.username)
        requestBody.add("password", keycloakProperties.admin.password)

        // Retrieve access token
        val response = webClient.post()
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(requestBody)
            .retrieve()
            .body(AccessToken::class.java)

        return response!!.access_token
    }

    data class AccessToken(val access_token: String)
}