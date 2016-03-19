
# Spring sample - schema versioning

It seems you shouldn't use `hibernate.hbm2ddl.auto: update` to handle your schema migrations (see http://stackoverflow.com/questions/221379/hibernate-hbm2ddl-auto-update-in-production).

Yet this feature is **really** handy in development. Soooo, your (main) options are:

- [Liquibase](http://www.liquibase.org/)
- [Flyway](https://flywaydb.org/)
- ...
- this repository? :innocent:

## How to

`docker-compose up -d` will create a MariaDB container

`mvn test` will run the unit tests, which will *miserably* fail if your schema (located in /src/main/resources/sql) do not match your @Entity mappings 

**BUT** Hibernate will kindly tell you what script to run to solve them :heart_eyes:

## Structure

    ├── src/main/java
    │   ├── AbstractEntity.java
    │   ├── Project.java
    │   └── Task.java
    ├── src/main/resources
    │   ├── schema_v0_to_v1.sql
    │   ├── schema_v0.sql
    │   ├── schema_v1_to_v2.sql
    │   ├── schema_v1.sql
    │   └── schema_v2.sql
    ├── src/test/java
    │   ├── ConfigTest.java
    │   └── DatabaseTest.java
    ├── docker-compose.yml
    └── pom.xml
