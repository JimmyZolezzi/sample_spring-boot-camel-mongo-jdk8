# SPRING-BOOT-CAMEL-MONGO-JDK8

---

## General
Sample Spring Boot application that hosts the following features:

* [JDK-8](http://www.oracle.com/technetwork/java/javase/downloads)
* [Gradle-2.1](http://www.gradle.org) multi module project

## Building the project
The project is organized as a polyglot gradle multi-module project.

### Global project task
Run the following commands/tasks to get a starting point for the project
* `gradle tasks` - Show a list of all available tasks

### Module specific tasks
A description of the module specific tasks will be added after the modules have been added.

## Development virtual machine
The sample provides a [Debian](http://www.debian.org) Wheezy based [Vagrant](http://www.vagrantup.com) 
VM that hosts the MongoDB used. The MongoDB ports are forwarded to your localhost. The VM is located 
in the Gradle module `prototype-vm`. In order to start the VM, go to `prototype-vm` a 
type `vagrant up`. The command `vagrant halt` will stop the VM. For provisioning, 
we use [Ansible](http://www.ansible.com/home).

The VM provisioning by Ansible is organized into roles. The following roles are currently present:

* `shell-utils` - A role for installing collection of common bash utilities like `vim`, `htop`, `curl`, `wget`, ...
* `java` - A role for installing Oracle JDK-8 via APT. Please note that this non-interactive installation automatically accepts the Oracle license agreement.
* `mongodb` - Still to be done.