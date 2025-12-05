package brz.marketplace.adapters.inbound.rest

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
private class SpringdocConfig {
    @Bean
    fun openApi(): OpenAPI {
        return OpenAPI().info(
            Info()
                .title("Marketplace API")
                .description("Marketplace application")
                .version("v0.0.1")
        )
    }
}