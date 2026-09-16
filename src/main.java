import java.util.*;

public class Main {

    // class 1
    public static class Student {

        int id;
        String name, department, project, projectStatus;
        double attendance, marks;

        // Parameterised Constructor
        public Student(int id, String name, String department) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.attendance = 0;
            this.marks = 0;
            this.project = "Not Assigned";
            this.projectStatus = "Not Started";
        }
    }

    static Scanner sc = new Scanner(System.in);

    // name list
    static ArrayList<Student> students = new ArrayList<>();

    // roll number of students
    static int nextStudentId = 1;


    public static void main(String[] args) {

        Database.initializeDatabase();

        students.addAll(Database.loadStudents());

        // updating next id
        for (Student s : students) {
            if (s.id >= nextStudentId) {
                nextStudentId = s.id + 1;
            }
        }

        // sample data only if database is empty
        if (students.isEmpty()) {
            addSampleData();

            for (Student s : students) {
                Database.saveStudent(s);
            }
        }

        System.out.println("\n=================================");
        System.out.println("          E D U F L O W");
        System.out.println("   Academic Attendance System");
        System.out.println("=================================");

        int choice = 0;

        while (choice != 5) {

            System.out.println("\n----------- MENU -----------");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Attendance");
            System.out.println("4. Attendance What-If Calculator");
            System.out.println("5. Exit");
            System.out.println("----------------------------");

            choice = readInt("Enter your choice: ");

            try {

                switch (choice) {

                    case 1:
                        addStudent();
                        break;

                    case 2:
                        viewStudents();
                        break;

                    case 3:
                        updateAttendance();
                        break;

                    case 4:
                        attendanceWhatIf();
                        break;

                    case 5:
                        System.out.println("\nExiting EduFlow...");
                        break;

                    default:
                        System.out.println("Invalid choice.");

                }

            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }

        sc.close();
    }


    public static void addStudent() {

        System.out.println("\n--- Add Student ---");

        String name = readString("Enter student name: ");
        String department = readString("Enter department: ");

        if (name.trim().isEmpty() || department.trim().isEmpty()) {
            System.out.println("Name and department cannot be empty.");
            return;
        }

        Student s = new Student(nextStudentId, name, department);

        students.add(s);
        Database.saveStudent(s);

        System.out.println("Student added successfully.");
        System.out.println("Student ID: " + s.id);

        nextStudentId++;
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
                status = "Eligible";
            } else {
                status = "Not Eligible";
            }

            System.out.printf("%-5d %-20s %-15s %-11.2f%% %-15s%n",
                    s.id,
                    s.name,
                    s.department,
                    s.attendance,
                    status);
        }
    }


    public static Student findStudent(int id) {

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

        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

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

        Database.saveStudent(s);

        System.out.printf("Attendance updated: %.2f%%%n", s.attendance);

        if (s.attendance >= 75) {
            System.out.println("Status: Eligible for exam.");
        } else {
            System.out.println("Status: Not eligible for exam.");
            System.out.println("Use the What-If Calculator to check required classes.");
        }
    }


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
            System.out.println("You already have 75% or more attendance.");
            return;
        }

        int needed = 0;

        while (((double) (attended + needed) /
                (total + needed)) * 100 < 75) {

            needed++;
        }

        double finalAttendance =
                ((double) (attended + needed) /
                (total + needed)) * 100;

        System.out.println("Classes you need to attend continuously: "
                + needed);

        System.out.printf("Attendance after that: %.2f%%%n",
                finalAttendance);
    }


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


    public static double readDouble(String message) {

        while (true) {

            try {

                System.out.print(message);

                String input = sc.nextLine();

                return Double.parseDouble(input);

            } catch (NumberFormatException e) {

                System.out.println("Please enter a valid number.");
            }
        }
    }


    public static String readString(String message) {

        System.out.print(message);

        return sc.nextLine();
    }


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
