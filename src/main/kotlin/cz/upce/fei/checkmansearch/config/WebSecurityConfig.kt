package cz.upce.fei.checkmansearch.config


import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain
import reactor.core.publisher.Mono

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class WebSecurityConfig() {

    @Value("\${spring.security.permit_paths}")
    private var permitPaths : Array<String> = arrayOf()

    @Value("\${server.ssl.enabled}")
    private var sslEnabled: Boolean = true

    @Value("\${spring.security.cors.enabled}")
    private var corsEnabled: Boolean = true

    @Bean
    fun securityWebFilterChain(http : ServerHttpSecurity): SecurityWebFilterChain {
        var conf = http.exceptionHandling()
            .authenticationEntryPoint { swe, _ -> Mono.fromRunnable { swe.response.statusCode = HttpStatus.UNAUTHORIZED } }
            .accessDeniedHandler { swe, _ -> Mono.fromRunnable { swe.response.statusCode = HttpStatus.FORBIDDEN } }
            .and()
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .authorizeExchange()
            .pathMatchers(HttpMethod.OPTIONS).permitAll()
            .pathMatchers(*permitPaths).permitAll()
            .anyExchange().authenticated().and()
            .cors().disable()

        return conf.build()
    }
}