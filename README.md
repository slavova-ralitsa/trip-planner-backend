# Trip Planner Application

**Made for the SAP Mentorship Program**

---

## Overview

This project is a Trip Planner application built in Java with Spring Boot, designed to help users manage destinations, plan trips, and track their favorites.
While still a work in progress, it demonstrates my skills in backend development, API design, security and data handling.

The goal of this project is to challenge myself and dive into real-world technologies while following clean and structured coding practices.

> AI was used minimally throughout this project — mainly as a learning aid to better understand concepts and technologies and to help debug issues.
---

## Features

*  **User Registration and Authentication** – Secure signup/login with password encryption
*  **Destination Management** – Add, view, and organize destinations
*  **Favorites and Personalization** – Users can mark favorite destinations
*  **Session Handling and Security** – JWT-based sessions, BCrypt password hashing
*  **REST API** – Designed for easy frontend integration
*  **Error Handling and Logging** – Centralized exception management with meaningful logs

---

## Installation Guide

Follow the steps below to run the project locally.

### Requirements

Make sure you have installed:

* Java 17+
* Maven
* PostgreSQL
* Git

---

##  Clone the Repository

```bash
git clone https://github.com/slavova-ralitsa/trip-planner
cd trip-planner
```

---

##  Database Setup

### 1. Create database

```sql
CREATE DATABASE trip_planner;
```

### 2. Configure environment variables

Create a `.env` file in the root directory:

```env
DB_USERNAME=your_username
DB_PASSWORD=your_password
```

---

## Application Configuration

Make sure your `application.properties` contains:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/trip_planner
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
```

---

## Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Or with Maven Wrapper:

```bash
./mvnw spring-boot:run
```

---

## API Testing

Since this is a backend-only application, you can test it with:

* Postman
* curl

---

## Tech Stack

* **Backend:** Java, Spring Boot, Spring Security
* **Database:** PostgreSQL
* **Security:** BCrypt, JWT
* **Testing:** JUnit, Mockito
* **Tools:** Postman

---

Special thanks to my mentor Pavlin Nikolov for their guidance, support, and valuable feedback throughout this project!
