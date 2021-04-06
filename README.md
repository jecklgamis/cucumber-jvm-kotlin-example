## cucumber-jvm-kotlin-example

This is an example Cucumber-JVM project.

* Uses Kotlin step definitions
* Packages tests into executable jar file
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
@CucumberOptions(features = ["classpath:features/Example.feature"],
        tags = "not @Wip", glue = ["classpath:steps"], plugin = ["pretty", "html:target/cucumber/html"])
class ExampleFeatureTest
```

## Running Cucumber Tests

Using executable jar file:
```shell script
mvn clean package
java -jar target/cucumber-jvm-kotlin-example.jar --plugin pretty --plugin html:cucumber/html --plugin json:cucumber/json/cucumber.json  --glue steps classpath:features --tags ~@Wip
```

Using Maven exec plugin:
```shell script
mvn exec:java -Dcucumber.options="--plugin pretty --plugin html:cucumber/html --plugin json:cucumber/json/cucumber.json --glue steps classpath:features --tags ~@Wip --tags @ExampleFeature"
```

Using JUnit test runner:
```shell script
mvn test -Dtest=ExampleFeatureTest
```

Using Docker:

Dockerfile
```docker
FROM jecklgamis/openjdk-8-jre:latest
MAINTAINER Jerrico Gamis <jecklgamis@gmail.com>

RUN mkdir -m 0755 -p /cucumber-jvm-kotlin-example

COPY target/cucumber-jvm-kotlin-example.jar /cucumber-jvm-kotlin-example
COPY docker-entrypoint.sh /cucumber-jvm-kotlin-example

CMD ["/cucumber-jvm-kotlin-example/docker-entrypoint.sh"]
```


Build Docker image (see `build-docker-image.sh`)
```shell script
IMAGE_NAME=jecklgamis/cucumber-jvm-kotlin-example
IMAGE_TAG=latest
docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .
```

Run Docker image (see `run-all-tests-using-docker.sh`)
```shell script
IMAGE_NAME=jecklgamis/cucumber-jvm-kotlin-example
IMAGE_TAG=latest

JAVA_OPTS=${JAVA_OPTS:-""}
ARGS=${ARGS:-"--plugin pretty --plugin html:cucumber/html --plugin json:cucumber/json/cucumber.json --glue steps classpath:features --tags @ExampleFeature"}

docker run -e "JAVA_OPTS=${JAVA_OPTS}" -e "ARGS=${ARGS}" ${IMAGE_NAME}:${IMAGE_TAG}
```

In Intellij, you can also run the scenario directly from the feature file. Ensure you have the Cucumber Java 
plugin installed.

## Other Cucumber-JVM Examples 
* https://github.com/jecklgamis/cucumber-jvm-java-example
* https://github.com/jecklgamis/cucumber-jvm-scala-example

## Links
* https://cucumber.io/docs
* http://github.com/cucumber/cucumber-jvm
* https://github.com/google/guice

