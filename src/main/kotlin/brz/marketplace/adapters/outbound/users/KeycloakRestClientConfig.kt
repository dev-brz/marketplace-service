package brz.marketplace.adapters.outbound.users

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
internal class KeycloakRestClientConfig(private val keycloakProperties: KeycloakConfigProperties) {
    companion object {
        const val KEYCLOAK_REST_CLIENT = "keycloakRestClient"
    }

    @Bean(KEYCLOAK_REST_CLIENT)
    fun keycloakRestClient(): RestClient {
        return RestClient.builder()
            .baseUrl(keycloakProperties.url)
            .build()
    }
}