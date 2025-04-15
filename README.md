HEAD
# Disaster-Reporting

# Disaster Relief System

A Java Swing application for managing disaster relief operations.

## Prerequisites

1. Java JDK 11 or higher
2. Maven
3. MySQL Server 8.0 or higher

## Setup Instructions

1. **Database Setup**
   - Install MySQL Server if not already installed
   - Open MySQL command line or workbench
   - Run the `setup_database.sql` script to create the database and tables
   - The default database name is "USER"

2. **Configure Database Connection**
   - Open `src/main/java/UserLogin.java`
   - Update the following constants if needed:
     ```java
     private static final String URL = "jdbc:mysql://localhost:3306/USER?useSSL=false";
     private static final String USER = "root";
     private static final String PASSWORD = "your_password";
     ```

3. **Build and Run**
   ```bash
   mvn clean install
   mvn exec:java -Dexec.mainClass="main.java.UserLogin"
   ```

## Default Credentials

- **Admin Login**
  - Username: admin
  - Password: admin123

## Features

- User Registration and Login
- Admin Dashboard
- Incident Reporting
- Data and Statistics
- Resource Management
- Build-a-Kit Guide

## Getting Started

Welcome to the VS Code Java world. Here is a guideline to help you get started to write Java code in Visual Studio Code.

## Folder Structure

The workspace contains two folders by default, where:

- `src`: the folder to maintain sources
- `lib`: the folder to maintain dependencies

Meanwhile, the compiled output files will be generated in the `bin` folder by default.

> If you want to customize the folder structure, open `.vscode/settings.json` and update the related settings there.

## Dependency Management

The `JAVA PROJECTS` view allows you to manage your dependencies. More details can be found [here](https://github.com/microsoft/vscode-java-dependency#manage-dependencies).
(Normalize line endings to match core.autocrlf=true)
