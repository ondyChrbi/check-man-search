package cz.upce.fei.checkmansearch.domain

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field

@Document(indexName = "checkman")
data class Feedback(
    @field:Id  @field:Field(name = "feedback_id") var id: Long,
    @field:Field(name = "course_id") var course_id: Long?,
    @field:Field(name = "description") var description: String,
    @field:Field(name = "type") var type: FeedbackType,
) {
    enum class FeedbackType {
        EXTREMELY_POSITIVE,
        POSITIVE,
        NEUTRAL,
        NEGATIVE
    }
}
