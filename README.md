# Library Management System

This is a library management system built with Spring Boot, React, and MySQL.
The system allows users to manage books, authors, and categories.

## Requirements

- Java 21
- MySQL
- Docker (for MySQL container)

## Project Structure

```plaintext
library-management/
├── .idea/
├── .mvn/
│   └── wrapper/
│       └── maven-wrapper.properties
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── mooc/
│   │   │           └── library_management/
│   │   │               ├── controller/
│   │   │               │   ├── BookController.java
│   │   │               │   ├── BorrowController.java
│   │   │               │   └── UserController.java
│   │   │               ├── domain/
│   │   │               │   ├── Book.java
│   │   │               │   ├── Borrow.java
│   │   │               │   └── User.java
│   │   │               ├── exception/
│   │   │               │   ├── GlobalExceptionHandler.java
│   │   │               │   └── ResourceNotFoundException.java
│   │   │               ├── init/
│   │   │               │   └── DataInitializer.java
│   │   │               ├── repository/
│   │   │               │   ├── BookRepository.java
│   │   │               │   ├── BorrowRepository.java
│   │   │               │   └── UserRepository.java
│   │   │               ├── service/
│   │   │               │   ├── BookService.java
│   │   │               │   ├── BorrowService.java
│   │   │               │   └── UserService.java
│   │   │               └── LibraryManagementApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── static/
│   │       └── templates/
│   └── test/
├── target/
├── .gitattributes
├── pom.xml

```

## Getting Started

### MySQL - DB

Create a MySQL container with the following command:

```bash
docker run --name mysql-container -e MYSQL_ROOT_PASSWORD=root_password -d -p 3306:3306 mysql:8
```

And connect to it with the following command:

```bash
docker exec -it mysql-container mysql -u root -p
```

Enter the password `root_password` when prompted.
Then create a database with the following command:

```sql
CREATE DATABASE library;
```


### Build and Run

Run the application with the following command:

```bash
./mvnw spring-boot:run
```

You can then access the application at `http://localhost:8080` and test it with Postman or any other API testing tool.
