CREATE DATABASE USER
USE USER

CREATE TABLE IF NOT EXISTS admin (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL
);
SELECT * FROM admin

CREATE TABLE IF NOT EXISTS user (
    id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL,
    password VARCHAR(50) NOT NULL
);
DELETE FROM user WHERE username = '';
SELECT * FROM user


CREATE TABLE IF NOT EXISTS Report_Incident (
    incident_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,  -- References user table
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    incident_type ENUM('Flood', 'Landslide', 'Earthquake', 'Other') NOT NULL,
    incident_location VARCHAR(255) NOT NULL,
    incident_description TEXT NOT NULL,
    incident_image BLOB,  -- Stores image data
    report_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (username) REFERENCES user(username) ON DELETE CASCADE
);
SELECT * FROM Report_Incident
DELETE FROM Report_Incident WHERE incident_description = 'Tsunami'

CREATE TABLE IF NOT EXISTS Registration (
    registration_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,  -- Foreign key to enforce registration only for existing users
    incident_id INT NOT NULL,  -- Foreign key to link registration to a reported incident
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,  -- User's email
    phone_number VARCHAR(15),     -- Optional phone number field
    age INT,
    gender ENUM('Male', 'Female', 'Other'),  -- Restricting gender to specific values
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE,
    FOREIGN KEY (incident_id) REFERENCES Report_Incident(incident_id) ON DELETE CASCADE
);

DROP TABLE Registration
SELECT * FROM Registration

ALTER TABLE Report_Incident MODIFY COLUMN incident_image LONGBLOB;

CREATE DATABASE APPOINTMENT_BOOKING;
USE APPOINTMENT_BOOKING;