import java.util.*;

public class Main {

    public static class Student     //class 1
    {
        int id;                             //instance variables for class 1
        String name, department, project, projectStatus;
        double attendance, marks;

        Student(int id, String name, String department) //Parameterised Constructor
        { 
            this.id = id;
            this.name = name;
            this.department = department;
            this.attendance = 0;
            this.marks = 0;
            this.project = "Not Assigned";
            this.projectStatus = "Not Started";
        }

        String getGrade()       //to give grades to students
        {
            if (marks >= 90) return "A+";
            if (marks >= 80) return "A";
            if (marks >= 70) return "B";
            if (marks >= 60) return "C";
            if (marks >= 50) return "D";
            return "F";                    //if none of the above marks 
        }

        boolean isEligible()       //to give exam or sit for exam
        {
            return attendance >= 75;
        }
    }

    public static class Exam     //class 2
    {
        int id;                            //instance variables for class 2
        String subject, date, time, room;

        Exam(int id, String subject, String date, String time, String room)   //parameterized constructor
        {
            this.id = id;
            this.subject = subject;
            this.date = date;
            this.time = time;
            this.room = room;
        }
    }

    static Scanner sc = new Scanner(System.in);      //for input of variables and data

    static ArrayList<Student> students = new ArrayList<>();    //name list
    static ArrayList<Exam> exams = new ArrayList<>();    //exams list

    static int nextStudentId = 1;      //roll number of students
    static int nextExamId = 1;          //exam number


    public static void main(String[] args) 
    {
        addSampleData();

        System.out.println("\n----------------------------------");    //designer print for display
        System.out.println("              EDUFLOW");
        System.out.println(" Academic Progress Management System");
        System.out.println("======------------------------======");

        while (true) 
        {
            showMenu();
            int choice = readInt("Enter your choice: ");
            try 
              {
                switch (choice) 
                {
                    case 1: addStudent();  break;
                    case 2:viewStudents(); break;
                    case 3:     updateAttendance();break;
                    case 4:
                        attendanceWhatIf(); break;

                    case 5: addExam();
                        break;

                    case 6: viewExams();  break;
                    case 7:  addMarks();break;

                    case 8:   manageProject();  break;

                    case 9:showProgress();break;
                    case 10:generateReport();break;
                    case 0:
                        System.out.println("\nThank you for using EduFlow!");
                        System.out.println("Exiting...");
                        scanner.close(); return;
                    default: System.out.println("Invalid choice. Please try again.");
                }

            } catch (Exception e) 
              {
                System.out.println("\nError: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
    }

    static void showMenu() 
  {

        System.out.println("\n--------------- MAIN MENU ---------------");
        System.out.println("1.  Add Student");
        System.out.println("2.  View Students");
        System.out.println("3.  Update Attendance");
        System.out.println("4.  Attendance What-If Calculator");
        System.out.println("5.  Schedule Exam");
        System.out.println("6.  View Exams");
        System.out.println("7.  Add / Update Marks");
        System.out.println("8.  Manage Project");
        System.out.println("9.  View Academic Progress");
        System.out.println("10. Generate Academic Report");
        System.out.println("0.  Exit");
        System.out.println("-----------------------------------------");
    }

    public static void addStudent() 
    {
        System.out.println("\n========== ADD STUDENT ==========");

        String name = readString("Student name: ");
        String department = readString("Department: ");

        Student student = new Student(nextStudentId++, name, department);

        students.add(student);       //adding student to the list

        System.out.println("\nStudent added successfully!");
        System.out.println("Student ID: " + student.id);
    }


    static void viewStudents() 
    {
        System.out.println("\n========== STUDENTS ==========");

        if(students.isEmpty()) 
        {
            System.out.println("No students found.");
            return;
        }

        for(Student s : students) 
        {
            System.out.println("--------------------------------");
            System.out.println("ID          : " + s.id);
            System.out.println("Name        : " + s.name);
            System.out.println("Department  : " + s.department);
            System.out.printf("Attendance  : %.2f%%%n", s.attendance);
            System.out.println("Marks       : " + s.marks);
            System.out.println("Grade       : " + s.getGrade());
            System.out.println("Project     : " + s.project);
            System.out.println("Project Status : " + s.projectStatus);
            System.out.println("Eligibility : " +
                    (s.isEligible() ? "Eligible" : "Not Eligible"));
        }
    }


    static Student findStudent(int id) 
    {
        for(Student s : students) 
        {
            if(s.id == id) 
            {
                return s;
            }
        }

        throw new IllegalArgumentException(
                "Student with ID " + id + " not found."
        );
    }


    // Attendance part
    static void updateAttendance() 
    {
        System.out.println("\n========== ATTENDANCE ==========");

        int id = readInt("Student ID: ");
        Student student = findStudent(id);

        double attendance = readDouble("Enter attendance percentage: ");

        if(attendance < 0 || attendance > 100) 
        {
            throw new IllegalArgumentException(
                    "Attendance must be between 0 and 100."
            );
        }

        student.attendance = attendance;

        System.out.printf(
                "Attendance updated to %.2f%%%n",
                attendance
        );

        if(student.isEligible()) 
        {
            System.out.println("Status: Eligible for examination.");
        }
        else 
        {
            System.out.println("Status: Not eligible for examination.");
        }
    }


    static void attendanceWhatIf() 
    {
        System.out.println("\n====== ATTENDANCE WHAT-IF ======");

        int id = readInt("Student ID: ");
        Student student = findStudent(id);

        int totalClasses = readInt("Total classes conducted: ");
        int attendedClasses = readInt("Classes attended: ");
        int futureClasses = readInt("Additional classes you plan to attend: ");

        if(totalClasses <= 0 || attendedClasses < 0 ||
                futureClasses < 0 || attendedClasses > totalClasses) 
        {
            throw new IllegalArgumentException("Invalid class values.");
        }

        int finalAttended = attendedClasses + futureClasses;
        int finalTotal = totalClasses + futureClasses;

        double projectedAttendance =
                (finalAttended * 100.0) / finalTotal;

        System.out.printf(
                "\nProjected Attendance: %.2f%%%n",
                projectedAttendance
        );

        if(projectedAttendance >= 75) 
        {
            System.out.println(
                    "Result: You will meet the 75% eligibility requirement."
            );
        }
        else 
        {
            System.out.println(
                    "Result: You will still be below the 75% requirement."
            );
        }
    }


    // Exam scheduling
    static void addExam() 
    {
        System.out.println("\n========== SCHEDULE EXAM ==========");

        String subject = readString("Subject: ");
        String date = readString("Date (DD-MM-YYYY): ");
        String time = readString("Time: ");
        String room = readString("Room: ");

        for(Exam e : exams) 
        {
            if(e.date.equalsIgnoreCase(date) &&
                    e.time.equalsIgnoreCase(time) &&
                    e.room.equalsIgnoreCase(room)) 
            {
                throw new IllegalArgumentException(
                        "Exam scheduling conflict! The room is already occupied at this time."
                );
            }
        }

        Exam exam = new Exam(
                nextExamId++,
                subject,
                date,
                time,
                room
        );

        exams.add(exam);

        System.out.println("\nExam scheduled successfully!");
        System.out.println("Exam ID: " + exam.id);
    }


    static void viewExams() 
    {
        System.out.println("\n========== EXAM SCHEDULE ==========");

        if(exams.isEmpty()) 
        {
            System.out.println("No exams scheduled.");
            return;
        }

        for(Exam e : exams) 
        {
            System.out.println("--------------------------------");
            System.out.println("Exam ID : " + e.id);
            System.out.println("Subject : " + e.subject);
            System.out.println("Date    : " + e.date);
            System.out.println("Time    : " + e.time);
            System.out.println("Room    : " + e.room);
        }
    }


    static void addMarks() 
    {
        System.out.println("\n========== RESULTS ==========");

        int id = readInt("Student ID: ");
        Student student = findStudent(id);

        double marks = readDouble("Enter marks (0-100): ");

        if(marks < 0 || marks > 100) 
        {
            throw new IllegalArgumentException(
                    "Marks must be between 0 and 100."
            );
        }

        student.marks = marks;

        System.out.println("\nMarks updated successfully.");
        System.out.println("Grade: " + student.getGrade());
    }


    static void manageProject() 
    {
        System.out.println("\n========== PROJECT TRACKER ==========");

        int id = readInt("Student ID: ");
        Student student = findStudent(id);

        String project = readString("Project name: ");

        System.out.println("\nSelect project status:");
        System.out.println("1. Not Started");
        System.out.println("2. In Progress");
        System.out.println("3. Completed");

        int status = readInt("Status: ");

        String projectStatus;

        switch(status) 
        {
            case 1:
                projectStatus = "Not Started";
                break;

            case 2:
                projectStatus = "In Progress";
                break;

            case 3:
                projectStatus = "Completed";
                break;

            default:
                throw new IllegalArgumentException(
                        "Invalid project status."
                );
        }

        student.project = project;
        student.projectStatus = projectStatus;

        System.out.println("\nProject updated successfully.");
    }


    // calculates the overall progress of student
    static void showProgress() 
    {
        System.out.println("\n========== ACADEMIC PROGRESS ==========");

        int id = readInt("Student ID: ");
        Student student = findStudent(id);

        double attendanceScore = student.attendance;
        double academicScore = student.marks;

        double projectScore;

        if(student.projectStatus.equals("Completed")) 
        {
            projectScore = 100;
        }
        else if(student.projectStatus.equals("In Progress")) 
        {
            projectScore = 60;
        }
        else 
        {
            projectScore = 0;
        }

        double overallProgress =
                (attendanceScore * 0.30) +
                (academicScore * 0.50) +
                (projectScore * 0.20);

        System.out.println("\nStudent: " + student.name);

        System.out.printf(
                "Attendance Score : %.2f%%%n",
                attendanceScore
        );

        System.out.printf(
                "Academic Score   : %.2f%%%n",
                academicScore
        );

        System.out.printf(
                "Project Score    : %.2f%%%n",
                projectScore
        );

        System.out.printf(
                "Overall Progress : %.2f%%%n",
                overallProgress
        );

        System.out.println(
                "Eligibility      : " +
                (student.isEligible() ? "Eligible" : "Not Eligible")
        );

        if(overallProgress >= 75) 
        {
            System.out.println("Progress Status  : Good");
        }
        else if(overallProgress >= 50) 
        {
            System.out.println("Progress Status  : Needs Improvement");
        }
        else 
        {
            System.out.println("Progress Status  : At Risk");
        }
    }


    static void generateReport() 
    {
        System.out.println("\n========== ACADEMIC REPORT ==========");

        int id = readInt("Student ID: ");
        Student student = findStudent(id);

        System.out.println("\n======================================");
        System.out.println("          EDUFLOW REPORT");
        System.out.println("======================================");

        System.out.println("Student ID   : " + student.id);
        System.out.println("Name         : " + student.name);
        System.out.println("Department   : " + student.department);

        System.out.printf(
                "Attendance   : %.2f%%%n",
                student.attendance
        );

        System.out.println(
                "Eligibility  : " +
                (student.isEligible() ? "Eligible" : "Not Eligible")
        );

        System.out.println("Marks        : " + student.marks);
        System.out.println("Grade        : " + student.getGrade());

        System.out.println("Project      : " + student.project);
        System.out.println("Project Status : " + student.projectStatus);

        System.out.println("======================================");
    }


    // taking input as text first makes it easier to handle errors
    static String readString(String message) 
    {
        System.out.print(message);

        String value = sc.nextLine().trim();

        if(value.isEmpty()) 
        {
            throw new IllegalArgumentException(
                    "Input cannot be empty."
            );
        }

        return value;
    }


    static int readInt(String message) 
    {
        System.out.print(message);

        String input = sc.nextLine().trim();

        try 
        {
            return Integer.parseInt(input);
        }
        catch(NumberFormatException e) 
        {
            throw new IllegalArgumentException(
                    "Please enter a valid number."
            );
        }
    }


    static double readDouble(String message) 
    {
        System.out.print(message);

        String input = sc.nextLine().trim();

        try 
        {
            return Double.parseDouble(input);
        }
        catch(NumberFormatException e) 
        {
            throw new IllegalArgumentException(
                    "Please enter a valid numeric value."
            );
        }
    }


    static void addSampleData() 
    {
        Student s1 = new Student(
                nextStudentId++,
                "Aarav Sharma",
                "CSE"
        );

        s1.attendance = 82;
        s1.marks = 88;
        s1.project = "Smart Campus";
        s1.projectStatus = "In Progress";

        students.add(s1);

        Student s2 = new Student(
                nextStudentId++,
                "Ananya Patel",
                "CSE"
        );

        s2.attendance = 68;
        s2.marks = 74;
        s2.project = "EduTrack";
        s2.projectStatus = "Completed";

        students.add(s2);

        exams.add(new Exam(
                nextExamId++,
                "Java Programming",
                "20-09-2026",
                "10:00 AM",
                "Lab-101"
        ));
    }
