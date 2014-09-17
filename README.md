# SPRING-BOOT-CAMEL-MONGO-JDK8

## Introduction
Sample Spring Boot application that integrates some of the most commonly used top-notch frameworks 
in the Java ecosystem. The example is also used for experimenting with all these frameworks, in
order to create a lightweight enterprise service, that runs independently from an external 
container but still provides enterprise ready features. 

Further more, most examples/tutorials out there focus on a specific technology for guaranteeing a
steeper learning curve. Experience shows that integrating different frameworks often yields unknown
problems. Another thing to consider is that often production grade features are also not regarded.
This sample aims at providing a full fledged prototype that could be used in production.

The following requirements will be 
fulfilled by the app.
 
* Standalone application
* REST API via embedded servlet container
* MongoDB used as data store
* TX management
* Vagrant+Ansible VM for development ease
* Multi module build, with profile activated integration tests

Please see the issues section for upcoming features and bugs.

## Used libraries (so far)
### General
* [JDK-8](http://www.oracle.com/technetwork/java/javase/downloads)
* [Gradle-2.1](http://www.gradle.org) multi module project
* [Spring Boot](http://projects.spring.io/spring-boot)

### Persistence
* [MongoDB](http://www.mongodb.org) for data persistence
* [spring-data-mongodb](http://projects.spring.io/spring-data-mongodb)

### Utilities
* [Guava](https://code.google.com/p/guava-libraries)

### Testing
* [TestNG](http://testng.org) - Testing framework/Test runner
* [Mockito](https://code.google.com/p/mockito) - Library for creating mock objects/behaviour for unit tests
* [AssertJ](http://joel-costigliola.github.io/assertj) - Fluent assertions for Java
* [REST-assured](https://code.google.com/p/rest-assured) - Easy testing of REST endpoints

## Building the project
The project is organized as a polyglot gradle multi-module project. You do not have to download 
Gradle standalone, because the project uses the Gradle wrapper.

### Prerequisites
* Download and install Oracle JDK-8
* Download and install Ansible

### Configuration
The `prototype-app` default configuration files (application.properties, logback.xml) are located under `src/main/resources`. This configuration is always active per default. It may be overridden by defining the `SPRING_CONFIG_LOCATION` environment property.

The integration test suite hosts an own application.properties file that overrides some properties (database URI) in order to run the integration tests against the test environment.

### Global project tasks
Run the following commands/tasks to get a starting point for the project (ensure `gradlew` is executable).
* `gradlew tasks` - Show a list of all available tasks

### Module specific tasks
#### prototype-vm
* `gradlew vagrantUp` or native command `vagrant up` for running the VM
* `gradlew vagrantHalt` or native command `vagrant halt` for halting the VM
* native command `vagrant ssh` for connecting to the VM via SSH

#### prototype-app
* `gradlew bootRun` to run the prototype app from the command line
* `gradlew test` run the unit tests
* `gradlew [clean] build` runs a full build

#### prototype-integration-test
* `gradlew -P integration [clean] test` runs the integration test suite

## Development virtual machine
The sample provides a [Debian 7](http://www.debian.org) (Wheezy) based [Vagrant](http://www.vagrantup.com) 
VM that hosts the MongoDB used. The MongoDB ports are forwarded to your localhost. The VM is located 
in the Gradle module `prototype-vm`. In order to start the VM, go to `prototype-vm` a 
type `vagrant up`. The command `vagrant halt` will stop the VM. For provisioning, 
we use [Ansible](http://www.ansible.com/home).

The VM provisioning by Ansible is organized into roles. The following roles are currently present:

* `shell-utils` - A role for installing collection of common bash utilities like `vim`, `htop`, `curl`, `wget`, ...
* `java` - A role for installing Oracle JDK-8 via APT. Please note that this non-interactive installation automatically accepts the Oracle license agreement.
* `mongodb` - A role that install and configures the MongoDB server
* `prototype` - A role that install/sets up all stuff needed by the `prototype-app`, e.g. accounts, permissions, ...

## Integration testing
The integration test suite is organized into a own gradle sub module `prototype-integration-tests`.
In our opinion this holds the following advantages, but is still subject to discussion.

* Divide unit test and integration test sources
* More easy build config (execution)
* More simple build scripts for each module
* Leverage default Gradle `test` configuration, no need for additional sourceSets etc.

The suite is based on the [Spring Testing Framework](http://docs.spring.io/spring-framework/docs/current/spring-framework-reference/html/testing.html)
brought in via `spring-boot-starter-test`. It launches the complete Spring Boot application,
having specific configuration property overrides for the integration tests. These overrides
are triggered because of the additional active profile `integration-test` from the base integration
test class

The integration tests are disabled by default, so that a full build with unit tests can be run from
scratch. In order to launch the integration tests, please run the following command (which sets the 
required Gradle property to activate the integration tests):

`$> gradle -P integration [clean] test`

The REST API integration tests are run against a embedded Jetty container. The container boots with 
a random port number to avoid collisions with running systems, what gets really interesting
on CI environments.

Also you should have a look at AbstractIntegTest class, which is the fundamental heart of the 
integration test suite. It took some time figuring out getting all set up using the TestNG runner.

REST-assured framework provides a fluent DSL for easily testing REST endpoints.