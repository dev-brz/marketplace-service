package brz.marketplace.adapters.outbound.users

import brz.marketplace.common.utils.TypeUtils.Companion.typeReference
import brz.marketplace.domain.model.User
import brz.marketplace.ports.outbound.users.UserRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.MediaType
import org.springframework.stereotype.Repository
import org.springframework.util.LinkedMultiValueMap
import org.springframework.web.client.RestClient

@Repository
private class KeycloakUserRepository(
    @Qualifier(KeycloakRestClientConfig.Companion.KEYCLOAK_REST_CLIENT) private val keycloakRestClient: RestClient,
    private val keycloakProperties: KeycloakConfigProperties
) : UserRepository {
    override fun findAll(): List<User> {
        return keycloakRestClient.get()
            .uri("/admin/realms/${keycloakProperties.realm}/users")
            .header("Authorization", "Bearer " + getAccessToken())
            .retrieve()
            .body(typeReference<List<User>>())!!
    }

    private fun getAccessToken(): String {
        val requestBody = LinkedMultiValueMap<String, String>()
        requestBody.add("grant_type", "password")
        requestBody.add("client_id", "admin-cli")
        requestBody.add("username", keycloakProperties.admin.username)
        requestBody.add("password", keycloakProperties.admin.password)

        val response = keycloakRestClient.post()
            .uri("/realms/master/protocol/openid-connect/token")
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(requestBody)
            .retrieve()
            .body(AccessToken::class.java)

        return response!!.access_token
    }

    data class AccessToken(val access_token: String)
}