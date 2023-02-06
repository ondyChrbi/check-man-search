package cz.upce.fei.checkmansearch.domain

import org.springframework.data.annotation.Id
import org.springframework.data.elasticsearch.annotations.Document
import org.springframework.data.elasticsearch.annotations.Field

@Document(indexName = "checkman")
data class Feedback(
    @field:Id  @field:Field(name = "id") var id: Long,
    @field:Field(name = "description") var description: String,
    @field:Field(name = "min_point") var minPoint: Int,
    @field:Field(name = "max_point") var maxPoint: Int,
    @field:Field(name = "removed") var removed: Boolean,
    @field:Field(name = "name") var name: String,
    @field:Field(name = "challenge_id") var challengeId: Long,
    @field:Field(name = "active") var active: Boolean,
)
