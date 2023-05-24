package cz.upce.fei.checkmansearch.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient
import org.springframework.data.elasticsearch.client.reactive.ReactiveRestClients
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate
import org.springframework.data.elasticsearch.core.convert.ElasticsearchConverter
import org.springframework.data.elasticsearch.core.convert.MappingElasticsearchConverter
import org.springframework.data.elasticsearch.core.mapping.SimpleElasticsearchMappingContext
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import java.util.function.Function


@Configuration
@EnableElasticsearchRepositories(basePackages = ["cz.upce.fei.checkmansearch.repository"])
@ComponentScan(basePackages = ["cz.upce.fei.checkmansearch.service"])
class ElasticsearchConfig {
    @Bean
    fun reactiveElasticsearchClient(): ReactiveElasticsearchClient {
        val clientConfiguration: ClientConfiguration = ClientConfiguration.builder()
            .connectedTo("localhost:9200")
            .withWebClientConfigurer(Function<WebClient, WebClient> { webClient: WebClient ->
                val exchangeStrategies: ExchangeStrategies = ExchangeStrategies.builder()
                    .codecs { configurer ->
                        configurer.defaultCodecs()
                            .maxInMemorySize(-1)
                    }
                    .build()
                webClient.mutate().exchangeStrategies(exchangeStrategies).build()
            })
            .build()
        return ReactiveRestClients.create(clientConfiguration)
    }

    @Bean
    fun elasticsearchConverter(): ElasticsearchConverter? {
        return MappingElasticsearchConverter(elasticsearchMappingContext()!!)
    }

    @Bean
    fun elasticsearchMappingContext(): SimpleElasticsearchMappingContext? {
        return SimpleElasticsearchMappingContext()
    }

    @Bean
    fun reactiveElasticsearchOperations(): ReactiveElasticsearchOperations? {
        return ReactiveElasticsearchTemplate(reactiveElasticsearchClient(), elasticsearchConverter())
    }
}