# SPRING-BOOT-CAMEL-MONGO-JDK8

## Introduction
Sample Spring Boot application that integrates some of the most commonly used top-notch frameworks 
in the Java ecosystem. The example is also used for experimenting with all these frameworks, in
order to create a lightweight enterprise service, that runs independently from an external 
container but still provides enterprise ready features. The following requirements will be 
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
* [MongoDB](http://www.mongodb.org) for data persistence
* [Spring Boot](http://projects.spring.io/spring-boot)

### Persistence
* [spring-data-mongodb](http://projects.spring.io/spring-data-mongodb)

### Utilities
* [Guava](https://code.google.com/p/guava-libraries)

### Testing
* [TestNG](http://testng.org) - Testing framework/Test runner
* [Mockito](https://code.google.com/p/mockito) - Library for creating mock objects/behaviour for unit tests
* [AssertJ](http://joel-costigliola.github.io/assertj) - Fluent assertions for Java

## Building the project
The project is organized as a polyglot gradle multi-module project. You do not have to download 
Gradle standalone, because the project uses the Gradle wrapper.

### Prerequisites
* Download and install Oracle JDK-8
* Download and install Ansible

### Global project task
Run the following commands/tasks to get a starting point for the project
* `gradle tasks` - Show a list of all available tasks

### Module specific tasks
#### prototype-vm
* `gradle vagrantUp` or native command `vagrant up` for running the VM
* `gradle vagrantHalt` or native command `vagrant halt` for halting the VM
* native command `vagrant ssh` for connecting to the VM via SSH
#### prototype-app
* `gradle bootRun` to run the prototype app from the command line
* `gradle test` run the unit tests
* `gradle [clean] build` runs a full build 

## Development virtual machine
The sample provides a [Debian](http://www.debian.org) Wheezy based [Vagrant](http://www.vagrantup.com) 
VM that hosts the MongoDB used. The MongoDB ports are forwarded to your localhost. The VM is located 
in the Gradle module `prototype-vm`. In order to start the VM, go to `prototype-vm` a 
type `vagrant up`. The command `vagrant halt` will stop the VM. For provisioning, 
we use [Ansible](http://www.ansible.com/home).

The VM provisioning by Ansible is organized into roles. The following roles are currently present:

* `shell-utils` - A role for installing collection of common bash utilities like `vim`, `htop`, `curl`, `wget`, ...
* `java` - A role for installing Oracle JDK-8 via APT. Please note that this non-interactive installation automatically accepts the Oracle license agreement.
* `mongodb` - A role that install and configures the MongoDB server
* `prototype` - A role that install/sets up all stuff needed by the `prototype-app`, e.g. accounts, permissions, ...