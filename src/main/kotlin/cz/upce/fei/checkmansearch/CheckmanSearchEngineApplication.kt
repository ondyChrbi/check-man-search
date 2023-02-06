package cz.upce.fei.checkmansearch

import cz.upce.fei.checkmansearch.repository.FeedbackRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing

@SpringBootApplication
@EnableR2dbcAuditing
class CheckmanSearchEngineApplication : CommandLineRunner {
	@Autowired
	private lateinit var feedbackRepository: FeedbackRepository

	override fun run(vararg args: String?) {
		val results = feedbackRepository.findAllByDescriptionEquals("Description of example requirement no. 1.2").blockFirst();

		println(results)
	}
}

fun main(args: Array<String>) {
	runApplication<CheckmanSearchEngineApplication>(*args)
}
