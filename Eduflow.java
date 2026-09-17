import java.util.*;
import java.io.*;

public class Eduflow 
{

    // class for storing student details
    public static class Student 
    {
        int id;
        String name, department;
        double attendance;

        public Student(int id, String name, String department) //parametrised constructor
        {
            this.id = id;
            this.name = name;
            this.department = department;
            this.attendance = 0;
        }
    }


    // class for exam details
    public static class Exam {

        int id;
        String subject, date, time, room;

        public Exam(int id, String subject, String date, String time, String room) //parameterised constructor
        {

            this.id = id;
            this.subject = subject;
            this.date = date;
            this.time = time;
            this.room = room;
        }
    }


     Scanner sc = new Scanner(System.in);

    // keeping all the students in an ArrayList
    static ArrayList<Student> students = new ArrayList<>();

    // exams are stored separately
    static ArrayList<Exam> exams = new ArrayList<>();

    static int nextStudentId = 1;
    static int nextExamId = 1;

    static final String ATTENDANCE_FILE = "attendance.txt";
    static final String EXAM_FILE = "exams.txt";


    public static void main(String[] args) 
    {

        loadData();
        loadExams();

        // adding some data when the program is opened for the first time
        if (students.isEmpty()) {
            addSampleData();
            saveData();
        }

        System.out.println("\n=================================");
        System.out.println("          E D U F L O W");
        System.out.println("    Academic Management System");
        System.out.println("=================================");

        int choice = 0;

        while (choice != 8) {

            System.out.println("\n----------- MENU -----------");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Attendance");
            System.out.println("4. Attendance What-If Calculator");
            System.out.println("5. Attendance Report");
            System.out.println("6. Add Exam");
            System.out.println("7. View Exams");
            System.out.println("8. Exit");
            System.out.println("----------------------------");

            choice = readInt("Enter your choice: ");

            try {

                switch (choice) {

                    case 1:
                        addStudent(); break;

                    case 2:
                        viewStudents();  break;

                    case 3:
                        updateAttendance(); break;

                    case 4:
                        attendanceWhatIf();break;

                    case 5:  attendanceReport();break;

                    case 6:
                        addExam(); break;

                    case 7:
                        viewExams();  break;

                    case 8:
                        saveData();   saveExams();

                        System.out.println("\nData saved.");
                        System.out.println("Exiting EduFlow...");
                        break;

                    default: System.out.println("Invalid choice.");
                }

            } catch (Exception e) 
            {
                System.out.println("Something went wrong: "
                        + e.getMessage());
            }
        }

        sc.close();
    }


    public static void addStudent()   //feature 1 to add list of studnets in system
    {

        System.out.println("\n--- Add Student ---");

        String name = readString("Enter student name: ");
        String department = readString("Enter department: ");

        if (name.trim().isEmpty() || department.trim().isEmpty()) {

            System.out.println("Name and department cannot be empty.");
            return;
        }

        Student s = new Student(
                nextStudentId,  name, department );

        students.add(s);

        System.out.println("Student added successfully.");
        System.out.println("Student ID: " + s.id);

        nextStudentId++;

        saveData();
    }


    public static void viewStudents() {

        System.out.println("\n--- Student List ---");

        if (students.isEmpty()) {

            System.out.println("No students available.");
            return;
        }

        System.out.printf("%-5s %-20s %-15s %-12s %-15s%n",
                "ID", "Name", "Department", "Attendance", "Status");

        System.out.println("----------------------------------------------------------------");

        for (Student s : students) {

            String status;

            if (s.attendance >= 75) {
                status = "Eligible";} 
            else {status = "Not Eligible";}

            System.out.printf("%-5d %-20s %-15s %-12.2f%% %-15s%n",
                    s.id,    s.name,  s.department,
                    s.attendance,
                    status);
        }
    }


    // finds a student using their ID
    public static Student findStudent(int id) 
    {

        for (Student s : students) {

            if (s.id == id) {
                return s;
            }
        }

        return null;
    }


    public static void updateAttendance() {

        System.out.println("\n--- Update Attendance ---");
        int id = readInt("Enter student ID: ");

        Student s = findStudent(id);
        if (s == null) {System.out.println("Student not found.");  return;}

        System.out.println("Student: " + s.name);

        int conducted = readInt("Enter total classes conducted: ");
        int attended = readInt("Enter classes attended: ");

        if (conducted <= 0) {

            System.out.println("Total classes must be greater than 0.");
            return;
        }

        if (attended < 0 || attended > conducted) {

            System.out.println("Invalid number of attended classes.");
            return;
        }

        s.attendance = ((double) attended / conducted) * 100;

        saveData();

        System.out.printf("Attendance updated: %.2f%%%n",
                s.attendance);

        if (s.attendance >= 75) {

            System.out.println("Status: Eligible for exam.");

        } else {

            System.out.println("Status: Not eligible for exam.");
            System.out.println(
                    "Use the What-If Calculator to check required classes."
            );
        }
    }


    // calculates how many classes are needed to reach 75%
    public static void attendanceWhatIf() {

        System.out.println("\n--- Attendance What-If Calculator ---");

        int id = readInt("Enter student ID: ");

        Student s = findStudent(id);

        if (s == null) {

            System.out.println("Student not found.");
            return;
        }

        System.out.println("Student: " + s.name);

        int total = readInt("Enter total classes conducted: ");
        int attended = readInt("Enter classes attended: ");

        if (total <= 0 || attended < 0 || attended > total) {

            System.out.println("Invalid attendance data.");
            return;
        }

        double currentAttendance =
                ((double) attended / total) * 100;

        System.out.printf("Current attendance: %.2f%%%n",
                currentAttendance);

        if (currentAttendance >= 75) {

            System.out.println(
                    "You already have 75% or more attendance."
            );

            return;
        }

        int needed = 0;

        // keep adding future classes until attendance reaches 75
        while (((double) (attended + needed) /
                (total + needed)) * 100 < 75) {

            needed++;
        }

        double finalAttendance =
                ((double) (attended + needed) /
                (total + needed)) * 100;

        System.out.println(
                "Classes you need to attend continuously: "
                        + needed
        );

        System.out.printf("Attendance after that: %.2f%%%n",
                finalAttendance);
    }


    public static void attendanceReport() {

        System.out.println("\n--- Attendance Report ---");

        if (students.isEmpty()) {

            System.out.println("No students available.");
            return;
        }

        double totalAttendance = 0;

        int eligible = 0;
        int notEligible = 0;

        Student highest = students.get(0);

        for (Student s : students) {

            totalAttendance += s.attendance;

            if (s.attendance >= 75) {
                eligible++;
            } else {
                notEligible++;
            }

            if (s.attendance > highest.attendance) {
                highest = s;
            }
        }

        double average =
                totalAttendance / students.size();

        System.out.println("Total students: " + students.size());

        System.out.printf("Average attendance: %.2f%%%n",
                average);

        System.out.println("Eligible students: " + eligible);

        System.out.println("Students below 75%: " + notEligible);

        System.out.println("Highest attendance: "
                + highest.name
                + " - "
                + String.format("%.2f%%", highest.attendance));

        System.out.println("\n--- Students Below 75% ---");

        boolean found = false;

        for (Student s : students) {

            if (s.attendance < 75) {

                System.out.printf("%d. %s - %.2f%%%n",
                        s.id,
                        s.name,
                        s.attendance);

                found = true;
            }
        }

        if (!found) {

            System.out.println("No students are below 75%.");
        }
    }


    // adding a new exam to the schedule
    public static void addExam() {

        System.out.println("\n--- Add Exam ---");

        String subject = readString("Enter subject: ");
        String date = readString("Enter date (DD/MM/YYYY): ");
        String time = readString("Enter time (HH:MM): ");
        String room = readString("Enter room: ");

        if (subject.trim().isEmpty() ||
                date.trim().isEmpty() ||
                time.trim().isEmpty() ||
                room.trim().isEmpty()) {

            System.out.println("All exam details are required.");
            return;
        }

        // basic date format check
        if (!date.matches("\\d{2}/\\d{2}/\\d{4}")) {

            System.out.println(
                    "Invalid date format. Use DD/MM/YYYY."
            );

            return;
        }

        // checking the time before saving it
        if (!time.matches("\\d{2}:\\d{2}")) {

            System.out.println(
                    "Invalid time format. Use HH:MM."
            );

            return;
        }

        String[] timeParts = time.split(":");

        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        if (hour < 0 || hour > 23 ||
                minute < 0 || minute > 59) {

            System.out.println(
                    "Invalid time. Use a valid 24-hour time."
            );

            return;
        }


        // check if another exam is already using the same room
        for (Exam e : exams) {

            if (e.date.equalsIgnoreCase(date) &&
                    e.time.equalsIgnoreCase(time) &&
                    e.room.equalsIgnoreCase(room)) {

                System.out.println("\nCONFLICT DETECTED!");

                System.out.println(
                        "Room " + room +
                        " is already booked for " +
                        e.subject +
                        " at " + e.time +
                        " on " + e.date
                );

                return;
            }
        }


        Exam e = new Exam(
                nextExamId,
                subject,
                date,
                time,
                room
        );

        exams.add(e);

        nextExamId++;

        saveExams();

        System.out.println("Exam scheduled successfully.");
        System.out.println("Exam ID: " + e.id);
    }


    public static void viewExams() {

        System.out.println("\n--- Exam Schedule ---");

        if (exams.isEmpty()) {

            System.out.println("No exams scheduled.");
            return;
        }

        System.out.printf("%-5s %-20s %-15s %-10s %-10s%n",
                "ID", "Subject", "Date", "Time", "Room");

        System.out.println(
                "------------------------------------------------------------"
        );

        for (Exam e : exams) {

            System.out.printf(
                    "%-5d %-20s %-15s %-10s %-10s%n",
                    e.id,
                    e.subject,
                    e.date,
                    e.time,
                    e.room
            );
        }
    }


    // saves student information into a text file
    public static void saveData() {

        try {

            FileWriter fw = new FileWriter(ATTENDANCE_FILE);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Student s : students) {

                bw.write(
                        s.id + "|" +
                        s.name + "|" +
                        s.department + "|" +
                        s.attendance
                );

                bw.newLine();
            }

            bw.close();

        } catch (IOException e) {

            System.out.println("Could not save attendance data.");
        }
    }


    // loads saved students when program starts
    public static void loadData() {

        File file = new File(ATTENDANCE_FILE);

        if (!file.exists()) {
            return;
        }

        try {

            BufferedReader br =
                    new BufferedReader(new FileReader(file));

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length == 4) {

                    int id = Integer.parseInt(data[0]);
                    String name = data[1];
                    String department = data[2];
                    double attendance =
                            Double.parseDouble(data[3]);

                    Student s =
                            new Student(id, name, department);

                    s.attendance = attendance;

                    students.add(s);

                    if (id >= nextStudentId) {
                        nextStudentId = id + 1;
                    }
                }
            }

            br.close();

        } catch (IOException | NumberFormatException e) {

            System.out.println("Could not load attendance data.");
        }
    }


    // saves exam schedule
    public static void saveExams() {

        try {

            FileWriter fw = new FileWriter(EXAM_FILE);
            BufferedWriter bw = new BufferedWriter(fw);

            for (Exam e : exams) {

                bw.write(
                        e.id + "|" +
                        e.subject + "|" +
                        e.date + "|" +
                        e.time + "|" +
                        e.room
                );

                bw.newLine();
            }

            bw.close();

        } catch (IOException e) {

            System.out.println("Could not save exam data.");
        }
    }


    // loads exams that were saved earlier
    public static void loadExams() {

        File file = new File(EXAM_FILE);

        if (!file.exists()) {
            return;
        }

        try {

            BufferedReader br =
                    new BufferedReader(new FileReader(file));

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length == 5) {

                    int id = Integer.parseInt(data[0]);

                    Exam e = new Exam(
                            id,
                            data[1],
                            data[2],
                            data[3],
                            data[4]
                    );

                    exams.add(e);

                    if (id >= nextExamId) {
                        nextExamId = id + 1;
                    }
                }
            }

            br.close();

        } catch (IOException | NumberFormatException e) {

            System.out.println("Could not load exam data.");
        }
    }


    // avoids the program crashing when wrong input is entered
    public static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                String input = sc.nextLine();

                return Integer.parseInt(input);

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");
            }
        }
    }


    public static String readString(String message) {

        System.out.print(message);

        return sc.nextLine();
    }


    // some sample students so the project is not empty on first run
    public static void addSampleData() {

        Student s1 = new Student(
                nextStudentId++,
                "Aarav",
                "CSE"
        );

        s1.attendance = 82.5;


        Student s2 = new Student(
                nextStudentId++,
                "Riya",
                "ECE"
        );

        s2.attendance = 68.0;


        Student s3 = new Student(
                nextStudentId++,
                "Kabir",
                "CSE"
        );

        s3.attendance = 91.0;


        students.add(s1);
        students.add(s2);
        students.add(s3);
    }
}
