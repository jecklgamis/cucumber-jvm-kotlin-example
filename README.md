## cucumber-jvm-kotlin-example

This is an example Cucumber-JVM project.

* Uses Kotlin step definitions
* Packages tests into executable jar file
* Uses Guice dependency injection

## Running Cucumber Tests

Using executable jar file:
```
mvn clean package
java -jar target/cucumber-jvm-kotlin-example.jar --plugin pretty --plugin html:cucumber/html --plugin json:cucumber/json/cucumber.json  --glue steps classpath:features --tags ~@Wip
```

Using Maven exec plugin:
```
mvn exec:java -Dcucumber.options="--plugin pretty --plugin html:cucumber/html --plugin json:cucumber/json/cucumber.json --glue steps classpath:features --tags ~@Wip --tags @ExampleFeature"
```

Using JUnit test runner:
```
mvn test -Dtest=ExampleFeatureTest
```

In Intellij, you can also run the scenario directly from the feature file. Ensure you have the Cucumber Java 
plugin installed.

## Other Cucumber-JVM Examples 
* https://github.com/jecklgamis/cucumber-jvm-java-example
* https://github.com/jecklgamis/cucumber-jvm-scala-example

## Example Target Apps

Dropwizard Apps:
* https://github.com/jecklgamis/dropwizard-java-example
* https://github.com/jecklgamis/dropwizard-kotlin-example
* https://github.com/jecklgamis/dropwizard-scala-example

Spring Boot Apps:
* https://github.com/jecklgamis/spring-boot-java-example
* https://github.com/jecklgamis/spring-boot-kotlin-example
* https://github.com/jecklgamis/spring-boot-scala-example

## Links

* https://cucumber.io/docs
* http://github.com/cucumber/cucumber-jvm
* https://github.com/google/guice

