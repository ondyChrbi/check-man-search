package cz.upce.fei.checkmansearch.controller

import cz.upce.fei.checkmansearch.doc.FeedbackSearchEndpointV1
import cz.upce.fei.checkmansearch.domain.Feedback
import cz.upce.fei.checkmansearch.service.FeedbackServiceV1
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/api/v1/feedback")
@Tag(name = "Feedback search V1", description = "APIs used for searching relevant feedbacks.")
class FeedbackControllerV1(private val feedbackServiceV1: FeedbackServiceV1) {
    @GetMapping("")
    @FeedbackSearchEndpointV1
    fun getAllFeedback(
        @RequestParam("description", required = false, defaultValue = "") description: String = "",
        @RequestParam("courseId", required = false) courseId: Long?,
        @RequestParam("authorId", required = false) authorId: Long?
    ): Flux<Feedback> {
        return feedbackServiceV1.findAll(description, courseId, authorId)
    }
}