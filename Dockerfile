FROM jecklgamis/openjdk-8-jre:latest
MAINTAINER Jerrico Gamis <jecklgamis@gmail.com>

RUN mkdir -m 0755 -p /cucumber-jvm-kotlin-example

COPY target/cucumber-jvm-kotlin-example.jar /cucumber-jvm-kotlin-example
COPY docker-entrypoint.sh /cucumber-jvm-kotlin-example

CMD ["/cucumber-jvm-kotlin-example/docker-entrypoint.sh"]

