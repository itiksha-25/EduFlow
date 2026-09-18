# EduFlow — Project Statement

## 1. Overview of the Project 
**EduFlow** is a management based platform developed in Java and it is made to ease a number of academic activities like student record management, taking attendance, analyzing attendance and scheduling examinations.


The implementation of the project is in CLI (Command Line Interface) format and it's developed using different Java technologies such as OOP (Object-Oriented Programming), Collections, File I/O, Exception management and Input Validation.


---


## 2. Problems to Solve 


Record keeping, attendance taking and scheduling of examination are tedious tasks and can go wrong when done manually due to errors that people can make, hence use of the system is imperative.


EduFlow comes as a proper solution to all of the aforementioned issues.


---


## 3. Goals of the Project
* Develop a simple academic management platform. 
* Automate calculation of attendance percentages.
* Provide what-if analysis for attendance. 
* Schedule examinations and check for conflicts when scheduling examinations. 
* Demonstrate the practical use of Java tools.

---
## 4. Overview

The scope of EduFlow presently includes the following

- Management of student records.
- Recording attendance and calculating attendance percentage.
- Checking eligibility for attendance on the basis of a 75% conditionality.
- Analyze how to get 75% attendance in class.
- Schedule examinations.
- Very limited detection of conflicts regarding room, date and time.
- Saving and loading of student and examination information through the use of text files


The project aims to provide essential academic management features through easy-to-use command line interface.

---

## 5. Intended Users
- **Students:** To check and analyze their attendance and eligibility status.
- **Studies Teachers:** To handle records pertaining the students, their attendance along with examination schedules.
- **Academic Administrators:** To have small control over pertinent academic data.


---

## 6. Major Features

### Students Management
- Add students
- Automatic assignment of student IDs
- Users can view student details

### Attendance Management
- Update attendance records
- Calculate attendance percentage
- Check whether student is eligible for attendance (75%)
- Carry out what-if analysis.
- Display attendance details

### Examinations Management
- Conducting the examinations
- Displaying examination schedules
- Detecting time and room issues

### Data Management
- Along with saving the records of students and examinations, the application enables loading the records at the time of application startup.

# The audience
- **Students:** The purpose is to view and analyze student records about attendance and eligibility.
- **Faculty members and academic staff:** The goal is to manage the records of students, attendances, and examination.
- **Academic administrators:** The objective is to keep basic records in regard to academics and scheduling data.


---


## High-level functional capabilities

### Keeping track of students
- Adding records on students.
- Assigning ID numbers to students automatically.
- Accessing student information.

### Keeping track of attendances
- Updating records concerning attendances.
- Computing attendance rate.
- Checking whether a student receives 75% of the eligible attendance.
- Conducting what-if analysis regarding attendances.
- Accessing attendance information.

### Keeping track of examination
- Scheduling examinations.
- Accessing examination schedules.
- Identifying time and place conflicts


### Keeping data
- Storing records about students and examination in the independent text file.
- Loading previous records when the application is launched.

---

## 7. Technical approach
EduFlow has been structured into different classes using Java in accordance with their purposes. 
- Eduflow.java — Main program.
- Student.java — This class is used for operations related to students and records about attendance.
- Attendance.java — This is used for attendance operations and conducting what-if analysis. 
- Exam.java — This class facilitates examination management and conflict detection.
- FileManager.java — This class is used for storing

---

## 8. Expected Outcome

The expected outcome is a functional, modular and easy-to-use Java application that demonstrates the practical application of core Java programming concepts while solving common academic record and scheduling tasks.

---
