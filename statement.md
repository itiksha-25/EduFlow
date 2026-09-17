# EduFlow - Academic Management System

## Problem Statement

Managing student attendance and examination schedules manually can be time-consuming and may lead to calculation errors, missing records, and scheduling conflicts.

EduFlow is a simple Java-based academic management system designed to help manage student attendance and examination information in one place.

The system calculates attendance percentages, checks exam eligibility, provides a what-if calculator for students below the required attendance, generates attendance reports, and manages examination schedules with basic conflict detection.

## Scope

The project focuses on basic academic management for students.

The current version includes:

- Student management
- Attendance management
- Attendance eligibility checking
- Attendance what-if calculation
- Attendance reports
- Examination scheduling
- Exam date, time and room validation
- Examination room conflict detection
- File-based data storage

The project is implemented as a console-based Java application and does not currently include a web or mobile interface.

## Target Users

- College students
- Faculty members
- Academic coordinators
- Department staff

## High-Level Features

### 1. Student Management

Users can add students and view student details such as student ID, name, department and attendance percentage.

### 2. Attendance Management

The system allows attendance to be updated using the number of classes conducted and classes attended. It automatically calculates the attendance percentage and determines examination eligibility based on the 75% requirement used in the application.

### 3. Attendance What-If Calculator

Students can enter their current attendance information to find out how many consecutive classes they need to attend to reach 75% attendance.

### 4. Attendance Report

The system provides a summary containing:

- Total number of students
- Average attendance
- Number of eligible students
- Number of students below 75%
- Highest attendance
- Students below the required attendance

### 5. Exam Management

Users can add and view examination schedules containing the subject, date, time and room.

### 6. Exam Conflict Detection

Before an exam is scheduled, EduFlow checks whether the same room is already booked at the same date and time. If a conflict exists, the exam is not added.

## Technologies Used

- Java
- Java Collections (`ArrayList`)
- File I/O
- Exception Handling
- Object-Oriented Programming
- Command Line Interface

## Expected Outcome

EduFlow provides a simple way to manage basic student academic information while demonstrating the use of Java programming concepts such as classes and objects, collections, file handling, input validation and exception handling.
