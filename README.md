# Hyperon vs Drools comparison

hyperon and drools comparison sample project.
To read more about Hyperon, please go [here](https://hyperon.io).
To read more about Drools, please go [here](http://drools.org)

## Prerequisites

Make sure you have Java 11. Gradle is not necessary, you can use provided ```gradlew``` wrapper to build project.

## Running

There are 2 ways to run this demo.

### Simple Spring calculation test

execute below command in project's root:

```text
gradle clean build
```

Go to ```spring-calculation``` module and execute command:

```text
gradle spring-boot:run
```

After Spring initialization you will see test results in terminal window.

### Run jmh tests
2. Go to ```jmh``` directory, execute below command:
```text
gradle clean build
```

All you need to do now is run ```MainRunner``` class. Do not hesitate to play with JMH parameters.