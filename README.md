# resource-pool-example

A simple example to get started with database connection pools and thread pools in Java. Whatch the video tutorial:

[![Database connection and thread pools](https://img.youtube.com/vi/kRdRne-pIoY/hqdefault.jpg)](https://www.youtube.com/watch?v=kRdRne-pIoY)

## Prerequisites

- Maven
- Java 17+
- A MariaDB database running on your machine or an instance in [SkySQL](https://mariadb.com/products/skysql).

## Setup

Configure the database connection URL, username, and password in the constructor of the `VotingService.` class.
The app requires the following table in the database:

```sql
CREATE TABLE programming_language
(
    name  VARCHAR(50) NOT NULL UNIQUE,
    votes INT         NULL
);
```

## Running the app

Execute the following in the command line:

```
git clone git@github.com:Programming-Brain/resource-pool-example.git
cd resource-pool-example
mvn package
java -jar target/resource-pool-example-1.0-SNAPSHOT.jar
```
