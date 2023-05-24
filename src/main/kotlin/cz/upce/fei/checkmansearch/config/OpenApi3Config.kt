package cz.upce.fei.checkmansearch.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.annotations.servers.Server

@SecurityScheme(
    name = "bearerAuth", type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT", scheme = "bearer"
)
@OpenAPIDefinition(
    info = Info(
        title = "Check man Search engine REST API documentation",
        description = "Official REST documentation for search engine using Elastic search.",
        contact = Contact(name = "Ondřej Chrbolka", email = "ondrej.chrbolka@upce.cz"),
        version = "0.0.1"
    ), servers = [
        Server(url = "https://localhost:9002", description = "Local docker services (secured)"),
        Server(url = "http://localhost:9002", description = "Local docker services")
    ]
)
class OpenApi3Config