# EduFlow

### Java-Based Academic Management System


EduFlow is an very simple Java academic management app that was created to correctly use student records, monitor attendance, provide attendance analysis, and schedule exams.


The system is being made as a menu command line interface (CLI) app which uses core Java like Object-oriented programming, collections, file I/O, exception handling; and input validation.


---


## 📌 Problem Statement


The process of managing student information, attendance and scheduling exams is time-consuming, can lead to errors in calculations and missing information and mistakes during scheduling exams.


EduFlow offers a simple solution that allows to combine all the academic management processes into a single application.


---


## 🎯 Objectives


* Organize student records.
* Automatically calculate students’ attendance.
* Ensure attendance eligibility based on 75% threshold.
* Conduct what-if analysis for students with lower attendance.
* Make exam schedules and solve minor problems with assigning rooms and timings.
* Demonstrate Java programing concepts in practice.
* Store records locally without using external databases.


---
## ✨ Features


### 1. Student Management


* New students can be added.
* Automatic assignment of student id’s.
* Student information is available.


### 2. Attendance Management


* Attendance of students is updated.
* Attendance percentage is calculated.
* The eligibility based on 75% attendance is checked.
* What if analysis can be performed.
* Attendance information is available.


### 3. Examination Management


* Examinations can be scheduled.
* Information related to subject, date, time and room can be saved.
* Examination schedule can be viewed.
* Room/date/time conflicts can be detected.


### 4. Data Persistence


* Student records are saved to `attendance.txt`.
* Examination records are saved to `exams.txt`.
* Previously saved records will be loaded when the application gets started.


---


## 🛠️ Technologies Used


| Technology         | Purpose                                   |
| ------------------ | ----------------------------------------- |
| Java               | Core programming language                 |
| OOP                | The application is organized into classes |
| ArrayList          | Dynamic records storage                   |
| File I/O           | Records are saved and loaded              |
| Exception Handling | Handles runtime/input errors              |
| Command Line       | User interface                            |
| Git & GitHub       | Version control and source code management|

--- 

## 📁 Project Structure

```text
EduFlow/
│
├── Eduflow.java
├── Student.java
├── Attendance.java
├── Exam.java
├── FileManager.java
│
├── attendance.txt
├── exams.txt
│
├── README.md
└── statement.md
```

### Java Classes

| File               | Responsibility                                              |
| ------------------ | ----------------------------------------------------------- |
| `Eduflow.java`     | Main program, menu and overall program flow                 |
| `Student.java`     | Student information, attendance calculation and eligibility |
| `Attendance.java`  | Attendance updates, viewing and what-if analysis            |
| `Exam.java`        | Examination scheduling and conflict detection               |
| `FileManager.java` | Saving and loading data using text files                    |

---

# 🚀 How to Run

## Prerequisites

Make sure **Java JDK** is installed on your computer.

Check Java:

```cmd
java --version
```

Check the Java compiler:

```cmd
javac --version
```

Both commands should display the installed Java version.

If `java` or `javac` is not recognized, install a JDK and make sure Java is added to the system PATH.

---

## Step 1 — Download the Project

Clone the repository using Git:

```cmd
git clone https://github.com/itiksha-25/EduFlow.git
```

Then enter the project folder:

```cmd
cd EduFlow
```

Alternatively, the repository can be downloaded as a ZIP file from GitHub and extracted.

---

## Step 2 — Open Command Prompt

Open **Command Prompt (CMD)**.

Navigate to the folder containing the EduFlow Java files.

For example:

```cmd
cd "C:\Users\itiks\OneDrive\Desktop\VIT Bhopal\Projects\EduFlow"
```

> The path will be different depending on where the project is stored on your computer.

---

## Step 3 — Check the Project Files

Run:

```cmd
dir
```

Make sure the following Java files are present:

```text
Eduflow.java
Student.java
Attendance.java
Exam.java
FileManager.java
```

---

## Step 4 — Compile the Project

Compile all Java files together:

```cmd
javac *.java
```

If there are **no error messages**, the compilation was successful.

Java `.class` files will be generated automatically.

---

## Step 5 — Run the Application

Run:

```cmd
java Eduflow
```

The EduFlow menu will appear:

```text
========== EDUFLOW ==========
1. Add Student
2. View Students
3. Update Attendance
4. View Attendance
5. Attendance What-If
6. Schedule Exam
7. View Exams
8. Save Data
9. Exit
=============================
Enter choice:
```

The application is now ready to use.

---

# 🖥️ Using EduFlow

## Add a Student

Select:

```text
1
```

Enter the student's name and department.

Example:

```text
Enter name: Rahul Sharma
Enter department: CSE
Student added. ID: 1
```

---

## View Students

Select:

```text
2
```

This displays the stored student records.

---

## Update Attendance

Select:

```text
3
```

Enter the student ID, total classes and attended classes.

Example:

```text
Enter student ID: 1
Total classes: 100
Classes attended: 80
```

The system calculates:

```text
Attendance: 80.00%
Eligible.
```

---

## View Attendance

Select:

```text
4
```

The system displays attendance percentages and eligibility for the students.

---

## Attendance What-If Analysis

Select:

```text
5
```

Enter a student ID.

For a student below 75%, EduFlow calculates the number of additional classes that need to be attended to reach the 75% threshold.

---

## Schedule an Examination

Select:

```text
6
```

Enter:

```text
Subject
Date
Time
Room
```

The system checks whether the room is already booked at the specified date and time.

---

## View Examinations

Select:

```text
7
```

This displays all scheduled examinations.

---

## Save Data

Select:

```text
8
```

Student and examination records are saved to:

```text
attendance.txt
exams.txt
```

---

## Exit

Select:

```text
9
```

The application saves the current data and exits.

---

# 💾 Data Storage

EduFlow uses simple text files instead of an external database.

### `attendance.txt`

Stores student information and attendance data.

### `exams.txt`

Stores examination information including subject, date, time and room.

The files are automatically read when the application starts and updated when data is saved.

---

# 🧪 Testing

The following major functionalities should be tested:

| Test                      | Expected Result                     |
| ------------------------- | ----------------------------------- |
| Add valid student         | Student is added                    |
| View students             | Student records are displayed       |
| Enter valid attendance    | Percentage is calculated            |
| Attendance ≥ 75%          | Student is marked eligible          |
| Attendance < 75%          | Student is marked not eligible      |
| Invalid attendance values | Error message is displayed          |
| What-if analysis          | Required classes are calculated     |
| Add valid examination     | Examination is scheduled            |
| Same room/date/time       | Scheduling conflict is detected     |
| Save data                 | Records are stored in text files    |
| Restart application       | Previously saved records are loaded |


---


# ⚙️ Design Approach


EduFlow uses the modular class structure with specifically determined classes signifying their significant functions.


* `Eduflow.java` controls the overall flow of the application.
* `Student.java` manages student records.
* `Attendance.java` handles the operations related to attendance.
* `Exam.java` is responsible for the examination scheduling.
* `FileManager.java` takes care of data persistence.


The modular design leads to simplicity, readability and maintainability of the application while demonstrating the essential Java programming concepts.


---


# 🔮 Future Enhancements


Possible future improvements include:


* Graphical or web-based user interface.
* Database integration.
* Student and faculty login systems.
* Marks and result management.
* Subject-wise attendance tracking.
* Automated notifications for low attendance.
* Calendar-based examination scheduling.
* Automated academic reports.
---

# 📚 Learning Outcomes

Through this project, the following concepts were practically applied:

* Java Classes and Objects
* Constructors and Methods
* Object-Oriented Programming
* ArrayList and Collections
* File Input/Output
* Exception Handling
* Input Validation
* Modular Programming
* Testing and Debugging
* Git and GitHub


[EduFlow – GitHub](https://github.com/itiksha-25/EduFlow)
