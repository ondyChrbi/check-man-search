package cz.upce.fei.checkmansearch.repository

import cz.upce.fei.checkmansearch.domain.Feedback
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository
import reactor.core.publisher.Flux

interface FeedbackRepository : ReactiveElasticsearchRepository<Feedback, Long> {
    fun findByDescriptionContains(description: String) : Flux<Feedback>

    fun findAllByDescriptionEquals(description: String) : Flux<Feedback>
}