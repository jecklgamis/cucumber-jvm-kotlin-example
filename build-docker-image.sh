#!/usr/bin/env bash
IMAGE_NAME=jecklgamis/cucumber-jvm-kotlin-example
IMAGE_TAG=latest
docker build -t ${IMAGE_NAME}:${IMAGE_TAG} .
