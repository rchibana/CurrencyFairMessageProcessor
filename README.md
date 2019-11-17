# Currency Fair Message Processor

Project responsible for process and store new transactions messages.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

- Java 8+
```
https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html
```
- MySql 8
```
https://dev.mysql.com/downloads/
```

### Installing

After the download of Java 8+ and MySql, follow the next steps

##### Database:

- Open the Mysql shell or Workbench and execute the command to create schema
```
CREATE DATABASE IF NOT EXISTS `TRANSACTION`
```

##### Code:

- Cloning the project:

```
$ git clone https://github.com/rchibana/CurrencyFairMessageProcessor.git
```

##### Migration

- You must execute the command to run the project's migrations. So, into the project folder:
```
$ ./gradlew flywayMigrate -i
```

##### Running the project 

- Execute the following command:

```
$ ./gradlew bootRun --args='--spring.profiles.active=dev'
```

After the execution of the command above, you'll be able to see the swagger interface with some information of endpoints and examples as well.

## Running the tests

The idea to run the tests in this project was thought to be easy to maintain and execute as well, bellow the command that must be executed inside the project folder:

```
$ ./gradlew clean build test
```

## Built With

* [Spring](https://spring.io/projects) - The web framework used
* [Flyway](https://flywaydb.org/) - Migration plugin
* [MapStruct](https://mapstruct.org/) - Java beans mapping
* [Gradle](https://gradle.org/) - Dependency Management
* [Intellij](https://www.jetbrains.com/idea/) - IDE used to develop the project

## Authors

* [Rodrigo Chibana](http://github.com/rchibana)
