#!/usr/bin/env bash
ARGS="--plugin pretty --plugin html:cucumber/index.html --plugin json:cucumber/json/cucumber.json --glue steps classpath:features --tags @ExampleFeature"
docker run -e "JAVA_OPTS=${JAVA_OPTS}" -e "ARGS=${ARGS}" cucumber-jvm-kotlin-example:main
