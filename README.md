leave-application-api
This repository contains the source code for a leave application API. This API allows employees to submit leave requests and managers to approve or reject them.

Features
Employees can submit leave requests with start date, end date, leave type, and reason.
Managers can view all leave requests and approve or reject them.
The API uses Spring Boot and JPA for persistence (configurable database).
Installation
Clone this repository:
git clone https://github.com/<your_username>/leave-application-api.git
Install dependencies:
mvn clean install
(Replace mvn with gradlew if using Gradle)

Configure database connection details in application.properties.

Usage
Start the application:
mvn spring-boot:run
(Replace mvn with gradlew if using Gradle)

Use a REST client or any HTTP tool to interact with the API.

API Endpoints:
Endpoint	HTTP Method	Description
/employees/{id}/leaves	GET	Get all leave requests for a specific employee.
/employees/{id}/leaves	POST	Create a new leave request for an employee.
/leaves/{id}	GET	Get details of a specific leave request.
/leaves/{id}	PUT	Update details of a leave request (e.g., reason).
/leaves/{id}/approval	PUT	Approve or reject a leave request by a manager.

Request/Response format:
JSON

Technologies
Spring Boot
JPA
PostgreSQL (configurable)
