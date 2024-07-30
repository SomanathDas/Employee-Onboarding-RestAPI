
# Employee Onboarding REST API

## Overview

This project is an Employee Onboarding System built with Spring Boot for the backend and MySQL for the database. It allows employees to register and upload documents, and HR to review and approve/reject onboarding requests.

## Features

- Employee registration
- Document upload
- HR approval/rejection of onboarding requests
- Role-based authentication and authorization

## Technologies Used

- Spring Boot
- Spring Security
- MySQL
- OpenAPI (Swagger)
- Maven

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven
- MySQL

## Installation

Clone the repository

```bash
  git clone https://github.com/yourusername/onboarding-system.git
  cd onboarding-system
```
Set up the MySQL database

```sql
  CREATE DATABASE onboarding_system;
```
Update the application.properties file with your database credentials

```properties
  spring.datasource.url=jdbc:mysql://localhost:3306/onboarding_system
  spring.datasource.username=root
  spring.datasource.password=yourpassword
  spring.jpa.hibernate.ddl-auto=update

```

Build the project

```bash
  mvn clean install
```

To run the application, use

```bash
  mvn spring-boot:run

```


## API Reference

### Authentication and Authorization

- #### Login

```http
  POST /login
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `username` | `string` | **Required**. Username for login |
| `password` | `string` | **Required**. password for login |

### Employee Endpoints

- #### Submit Details

```http
  POST /api/employee/submit-details
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `name`      | `string` | **Required**. Id of item to fetch |
| `address`      | `string` | **Required**. Id of item to fetch |
| `email`      | `string` | **Required**. Id of item to fetch |
| `phoneNumber`      | `string` | **Required**. Id of item to fetch |
| `dateOfBirth`      | `string` | **Required**. Id of item to fetch |
| `jobTitle`      | `string` | **Required**. Id of item to fetch |
| `department`      | `string` | **Required**. Id of item to fetch |
| `startDate`      | `string` | **Required**. Id of item to fetch |
| `nationalId`      | `string` | **Required**. Id of item to fetch |
| `socialSecurityNumber`      | `string` | **Required**. Id of item to fetch |
| `bankAccountNumber`      | `string` | **Required**. Id of item to fetch |
| `bankName`      | `string` | **Required**. Id of item to fetch |
| `ifscCode`      | `string` | **Required**. Id of item to fetch |
| `emergencyContactName`      | `string` | **Required**. Id of item to fetch |
| `emergencyContactRelationship`      | `string` | **Required**. Id of item to fetch |
| `emergencyContactPhoneNumber`      | `string` | **Required**. Id of item to fetch |
| `documentMetadata`      | `List<DocumentMetadata>` | **Required**. Id of item to fetch |
| `documents`      | `List<MultipartFile>` | **Required**. Id of item to fetch |

- #### Get Employee Details

```http
  GET /api/employee/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `Long` | **Required**. Id of employee to fetch |

### HR Endpoints

- #### Get Pending Requests

```http
  GET /api/hr/pending-requests
```
- #### Approve Request

```http
  POST /api/hr/approve-request/{employeeId}
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `employeeId` | `Long` | **Required**. Id of employee to approve |

- #### Reject Request

```http
  DELETE /api/hr/reject-request/{employeeId}
```
| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `employeeId` | `Long` | **Required**. Id of employee to reject |

### Document Endpoints

- #### Get Document

```http
  GET /api/document/get/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `employeeId` | `Long` | **Required**. Id of document to fetch |


## API Endpoints

![Screenshot (32)](https://github.com/user-attachments/assets/3f851678-4671-4ba9-9591-a1ac0aeb6fff)



## Postman Collection

You can import the provided Postman collection to quickly test the API endpoints.

[Download the Postman Collection](https://link-to-your-postman-collection)

### How to Import the Postman Collection

    1. Download the Postman collection JSON file from the link above.
    2. Open Postman and click on the "Import" button.
    3. Select the downloaded JSON file and import it.
    4. The collection will be added to your Postman workspace, and you can start testing the API endpoints.
