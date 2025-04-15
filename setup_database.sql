-- Create the database if it doesn't exist
CREATE DATABASE IF NOT EXISTS USER;

-- Use the database
USE USER;

-- Create user table
CREATE TABLE IF NOT EXISTS user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- Create admin table if needed
CREATE TABLE IF NOT EXISTS admin (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL
);

-- Insert a default admin user (username: admin, password: admin123)
INSERT IGNORE INTO admin (username, password) VALUES ('admin', 'admin123'); 