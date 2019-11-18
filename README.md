# Currency Fair Message Processor

Project responsible for process and store new transactions messages.


## Problem Approach

Based on the problem, I created a solution that I'll describe better bellow:

1 - I decided to create a microservice that could scale if necessary (using a EC2 for example).

2 - Using JUnit and SpringMVCTest will help the team to change the code creating new features our fixing bugs with no much worries.

3 - Based in my own experience, I decided to include the swagger, that will provide a facility to test and understand the API communication contract.

4 - In some situations, when many developer are working together, any database alteration is hard to be synchronized with other developer,
so, that's the reason that I added flyway. It will provide the possibility of creating database versions.

5 - With the idea of separate the layers, I created some DTOs that I believe to be maintainable, and to reduce the quantity of code
to convert an entity to a DTO, I added the MapStruct plugin that will help with it.

6 - The Spring Sleuth was added to help and track the customer actions. Sometimes it's really difficult to understand all the request 
that a microservice is receiving, and even though we could look all the calls, we'd spend so much time to find the requests and match them

7 - The idea of mysql in this project, is basically because based on the quantity of fields and transactions that I imagine that will be arrive,
if necessary to add more fields a new table could be create. Moreover, even knowing that NoSql databases are faster than Relational databases (reading process),
I still believe that it's the best option once that I'll have a reliable database structure instead of documents that can be changed, and if a necessity of changing the database to another one like Oracle or SqlServer, would be easier than migrate a mongoDb database to Cassandra for example (almost impossible).
Of course that it took a long time to be thought and planned, once that as I told before, a NoSql database would be faster to be read.

### Some improvements to the future

1 - Add some monitoring like new relic would be interesting

2 - It's missing some performance tests

3 - Audit tables could be added using hibernate envers

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

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

```
http://localhost:8080/swagger-ui.html
```

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
