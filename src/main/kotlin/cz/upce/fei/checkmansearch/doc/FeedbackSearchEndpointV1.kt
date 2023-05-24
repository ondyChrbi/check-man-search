package cz.upce.fei.checkmansearch.doc

import cz.upce.fei.checkmansearch.domain.Feedback
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.http.MediaType
import java.lang.annotation.Inherited

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@Inherited
@Operation(summary = "Find challenge and semesters based on search criteria. For supported operators please visit: https://github.com/jirutka/rsql-parser", security = [SecurityRequirement(name = "bearerAuth")])
@ApiResponses(
    ApiResponse(
        responseCode = "200",
        description = "Record",
        content = [Content(
            mediaType = MediaType.APPLICATION_JSON_VALUE,
            array = ArraySchema(schema = Schema(implementation = Feedback::class))
        )]
    ),
    ApiResponse(
        responseCode = "401",
        description = "Not authorized.",
        content = [Content(mediaType = MediaType.TEXT_PLAIN_VALUE)]
    ),
    ApiResponse(
        responseCode = "403",
        description = "Missing permissions.",
        content = [Content(mediaType = MediaType.TEXT_PLAIN_VALUE)]
    ),
    ApiResponse(
        responseCode = "500",
        description = "Error occur on server side. Please try it again later or contact technical support.",
        content = [Content(mediaType = MediaType.TEXT_PLAIN_VALUE)]
    )
)
annotation class FeedbackSearchEndpointV1
