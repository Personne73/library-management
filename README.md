# Library Management System

This is a library management system built with Spring Boot, React, and MySQL.
The system allows users to manage books, authors, and categories.

## Project Structure

```plaintext
library-management/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── mooc/
│   │   │           └── library_management/
│   │   │               ├── LibraryManagementApplication.java
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       └── application.properties
└── test/
```

## MySQL - DB

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


## App

Run the application with the following command:

```bash
./mvnw spring-boot:run
```