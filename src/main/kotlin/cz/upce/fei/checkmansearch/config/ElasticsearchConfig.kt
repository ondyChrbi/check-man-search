package cz.upce.fei.checkmansearch.config

import org.elasticsearch.client.RestHighLevelClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.elasticsearch.client.ClientConfiguration
import org.springframework.data.elasticsearch.client.RestClients
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories


@Configuration
@EnableElasticsearchRepositories(basePackages = ["cz.upce.fei.checkmansearch.repository"])
@ComponentScan(basePackages = ["cz.upce.fei.checkmansearch.service"])
class ElasticsearchConfig : AbstractElasticsearchConfiguration() {
    @Bean
    override fun elasticsearchClient(): RestHighLevelClient {
        val clientConfiguration = ClientConfiguration.builder()
            .connectedTo("localhost:9200")
            .build()
        return RestClients.create(clientConfiguration)
            .rest()
    }
}