# Job Application Management System – Backend

A production-style backend application built using **Java 21 (LTS)** and **Spring Boot 3** that allows administrators to manage job postings and job seekers to apply for jobs securely.

This project follows clean architecture principles and demonstrates real-world backend development practices such as authentication, authorization, pagination, and exception handling.

---

##  Key Features
- JWT-based authentication and authorization
- Role-based access control (ADMIN, JOB_SEEKER)
- Secure REST APIs
- Job creation, update, and deletion (Admin only)
- Job search with pagination and filtering
- Job application tracking
- Global exception handling
- Clean layered architecture (Controller → Service → Repository)

---

##  Technology Stack
- Java 21 (LTS)
- Spring Boot 3.x
- Spring Security
- JWT (JSON Web Token)
- Spring Data JPA
- MySQL
- Maven
- REST API

---

##  Application Modules
- Authentication & Authorization
- User & Role Management
- Job Management
- Job Application Management
- Exception Handling

---

##  Project Structure
job-application-management-system/
│
├── controller
├── service
├── repository
├── model
├── security
├── exception
└── resources
