## cucumber-jvm-kotlin-example

[![Build](https://github.com/jecklgamis/cucumber-jvm-kotlin-example/actions/workflows/build.yml/badge.svg)](https://github.com/jecklgamis/cucumber-jvm-kotlin-example/actions/workflows/build.yml)

This is an example Cucumber-JVM project using Kotlin.

## What's In The Box?
* Runs tests using Maven plugin, executable jar file, or Docker container
* Uses Guice dependency injection

## Resources

The feature file (`Example.feature`):
 
```gherkin
Feature: Example feature

  @ExampleFeature
  Scenario: Example scenario
    Given this pre condition
    And this pre condition
    When I do this
    And I do that
    Then I can verify that
    And I can also verify that
```

The step definitions (`ExampleSteps.kt`). This contains the implementation of the steps.
```kotlin
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
```

The Injector source used to create the injector (i.e a Guice dependency injection context in this example)
```kotlin
class ExampleInjectorSource : InjectorSource {
    override fun getInjector(): Injector {
        return Guice.createInjector(Stage.PRODUCTION, CucumberModules.createScenarioModule(), ExampleTestModule())
    }
}
```

The Guice module used to bind dependencies injected in the step definitions
```kotlin
internal class ExampleTestModule : AbstractModule() {
    override fun configure() {
        bind(TestContext::class.java)
    }
}
```

The TestContext. A simple key-value pair storage injected in the steps. 
```kotlin
class TestContext {
    private val context: ThreadLocal<MutableMap<String, Any>> = ThreadLocal.withInitial { mutableMapOf<String, Any>() }

    fun put(key: String, value: Any) {
        context.get()[key] = value
    }

    operator fun get(key: String): Any? {
        return context.get()[key]
    }

}
```

A JUnit based test runner. This can be called from the IDE or using Maven exec plugin.
```kotlin
@RunWith(Cucumber::class)
@CucumberOptions(
    features = ["classpath:cucumber.jvm.kotlin.example.features/Example.features"],
        tags = "not @Wip", glue = ["classpath:steps"], plugin = ["pretty", "html:target/cucumber/html"])
class ExampleFeatureTest
```

## Running Cucumber Tests

Using executable jar file (`run-all-tests-using-jar.sh`):

```bash
PLUGINS="--plugin pretty --plugin html:cucumber/cucumber.html --plugin json:cucumber/cucumber.json"
java -jar target/cucumber-jvm-kotlin-example.jar ${PLUGINS} --glue steps classpath:features  --tags "not @Wip" --tags @ExampleFeature --threads 8
```

Using Maven exec plugin (`run-all-tests-using-plugin.sh`)::

```bash
PLUGINS="--plugin pretty --plugin html:cucumber/index.html --plugin json:cucumber/json/cucumber.json"
mvn exec:java -Dcucumber.options="${PLUGINS} --tags 'not @Wip' --tags @ExampleFeature --glue steps classpath:features"
```

Using JUnit test runner:

```bash
mvn test -Dtest=ExampleFeatureTest
```

Using Docker (`run-all-tests-using-docker.sh`)::

```bash
docker build -t cucumber-jvm-kotlin-example:main .
ARGS="--plugin pretty --plugin html:cucumber/index.html --plugin json:cucumber/json/cucumber.json --glue steps classpath:features --tags @ExampleFeature"
docker run -e "JAVA_OPTS=${JAVA_OPTS}" -e "ARGS=${ARGS}" cucumber-jvm-kotlin-example:main
```

## Other Cucumber-JVM Examples 

* https://github.com/jecklgamis/cucumber-jvm-java-example
* https://github.com/jecklgamis/cucumber-jvm-scala-example

