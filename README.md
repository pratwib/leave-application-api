
# leave-application-api

This repository provides the source code for a leave application API built with Spring Boot. It facilitates employee to apply for leave request and admin approval/rejection functionalities.


## Features

- Basic CRUD for all entities.
- Apply leave requests with start date, end date, leave type, and reason notes.
- View all leave requests.
- Approve or reject leave requests.


## Technologies

- Java 17
- Spring Boot
- JPA (data persistence with a configurable database)
- Postgre SQL
- Maven


## Run Locally

1. Clone the Repository:

```bash
git clone https://github.com/pratwib/leave-application-api.git
```

2. Install Dependencies:
Use either ```mvn clean install``` (Maven) or ```gradlew build``` (Gradle) to install project dependencies.

3. Database Configuration:
Configure your database connection details in ```application.properties```.
```bash
spring.application.name=leave-application-api
spring.datasource.username=YOUR_DATABASE_USERNAME
spring.datasource.password=YOUR_DATABASE_PASSWORD
spring.datasource.url=jdbc:postgresql://localhost:5432/YOUR_DATABASE_NAME
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
app.leaveapplication.jwt.jwt-secret=secret
app.leaveapplication.jwt.jwt-name=Leave Application API
app.leaveapplication.jwt.jwt-expired=30
```

4. Run the project
## Request/Response Format:

- JSON

## API Documentation

- Postman : https://documenter.getpostman.com/view/26627741/2sA35G326t
