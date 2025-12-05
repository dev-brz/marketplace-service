package brz.marketplace.common.annotations

import io.swagger.v3.oas.annotations.responses.ApiResponse

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@ApiResponse(
    responseCode = "500",
    description = "Internal Server Error"
)
annotation class InternalServerErrorApiResponse()
