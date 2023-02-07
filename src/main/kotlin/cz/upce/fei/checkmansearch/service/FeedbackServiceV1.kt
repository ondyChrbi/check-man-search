package cz.upce.fei.checkmansearch.service

import cz.upce.fei.checkmansearch.domain.Feedback
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.Pageable
import org.springframework.data.elasticsearch.client.reactive.ReactiveElasticsearchClient
import org.springframework.data.elasticsearch.core.ReactiveElasticsearchOperations
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class FeedbackServiceV1(
    private val reactiveElasticsearchOperations: ReactiveElasticsearchOperations,
    private val reactiveElasticsearchClient : ReactiveElasticsearchClient
) {
    fun findAll(description: String, courseId: Long?, authorId: Long?): Flux<Feedback> {
        val query =  NativeSearchQueryBuilder()

        if (description.isNotEmpty()) {
            query.withQuery(QueryBuilders.prefixQuery("description", description))
        }

        if (courseId != null) {
            query.withQuery(QueryBuilders.matchQuery("courseId", courseId.toString()))
        }

        if (authorId != null) {

        }

        query.withPageable(Pageable.ofSize(PAGE_SIZE))

        return reactiveElasticsearchOperations.search(
            query.build(),
            Feedback::class.java,
            IndexCoordinates.of("checkman")
        ).map { it.content }.filter { it != null }
    }

    private companion object {
        const val PAGE_SIZE = 10
    }
}
