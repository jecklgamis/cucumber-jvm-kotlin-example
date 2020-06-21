package steps

import io.cucumber.java8.En
import io.cucumber.java8.Scenario
import org.slf4j.LoggerFactory
import util.TestContext
import javax.inject.Inject

class ExampleSteps @Inject constructor(private val testContext: TestContext) : En {
    private val log = LoggerFactory.getLogger(ExampleSteps::class.java)

    init {
        configureSteps()
    }

    private fun configureSteps() {
        Before { scenario: Scenario -> log.info("Before scenario : " + scenario.name) }

        After { scenario: Scenario -> log.info("After scenario : " + scenario.name) }

        Given("^this pre condition$") { testContext.put("some-key", "some-value") }

        When("^I do this$") { }

        When("^I do that$") { }

        Then("^I can verify that$") { assert(testContext["some-key"] == "some-value") }

        Then("^I can also verify that$") {
        }

    }
}
