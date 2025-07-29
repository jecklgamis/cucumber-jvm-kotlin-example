package steps

import com.google.inject.Inject
import io.cucumber.java.After
import io.cucumber.java.Before
import io.cucumber.java.Scenario
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.slf4j.LoggerFactory
import util.TestContext

class ExampleSteps @Inject constructor(private val testContext: TestContext) {
    private val log = LoggerFactory.getLogger(ExampleSteps::class.java)

    @Before
    fun setupScenario(scenario: Scenario) = log.info("Before scenario: {}", scenario.name)

    @After
    fun tearDownScenario(scenario: Scenario) = log.info("After scenario: {}", scenario.name)

    @Given("this pre condition")
    fun thisPreCondition() = testContext.put("some-key", "some-value")

    @Given("that pre condition")
    fun thatPreCondition() {
    }

    @When("I do this")
    fun iDoThis() {
    }

    @When("I do that")
    fun iDoThat() {
    }

    @Then("I can verify that")
    fun iCanVerifyThat() {
    }

    @Then("I can also verify that")
    fun iCanAlsoVerifyThat() {
    }
}