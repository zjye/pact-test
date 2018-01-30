Prerequisite
============
Setup Pact Broker, pact-docker.md


Consumer = payment
=
mvn clean package pact:publish


Provider = billing
=
mvn clean package

right click run test