import java.util.ArrayList;
import java.util.Scanner;

public class Eduflow {

    static ArrayList<Student> students =
            new ArrayList<>();

    static ArrayList<Exam> exams =
            new ArrayList<>();

    static Scanner sc =
            new Scanner(System.in);

    public static void main(String[] args) {

        students =
                FileManager.loadStudents();

        exams =
                FileManager.loadExams();

        int choice = 0;

        do {

            System.out.println();
            System.out.println("========== EDUFLOW ==========");
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Attendance");
            System.out.println("4. View Attendance");
            System.out.println("5. Attendance What-If");
            System.out.println("6. Schedule Exam");
            System.out.println("7. View Exams");
            System.out.println("8. Save Data");
            System.out.println("9. Exit");
            System.out.println("=============================");

            System.out.print("Enter choice: ");

            try {

                choice =
                        Integer.parseInt(
                                sc.nextLine());

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a number.");

                continue;
            }

            switch (choice) {

                case 1:
                    addStudent();
                    break;

                case 2:
                    viewStudents();
                    break;

                case 3:
                    Attendance.updateAttendance(
                            students, sc);
                    break;

                case 4:
                    Attendance.viewAttendance(
                            students);
                    break;

                case 5:
                    Attendance.whatIf(
                            students, sc);
                    break;

                case 6:
                    Exam.addExam(exams, sc);
                    break;

                case 7:
                    Exam.viewExams(exams);
                    break;

                case 8:
                    saveData();
                    break;

                case 9:
                    saveData();
                    System.out.println(
                            "Thank you for using EduFlow!");
                    break;

                default:
                    System.out.println(
                            "Invalid choice.");
            }

        } while (choice != 9);

        sc.close();
    }

    static void addStudent() {

        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter department: ");
        String department = sc.nextLine();

        if (name.isEmpty() ||
                department.isEmpty()) {

            System.out.println(
                    "Fields cannot be empty.");

            return;
        }

        Student s =
                new Student(
                        students.size() + 1,
                        name,
                        department);

        students.add(s);

        System.out.println(
                "Student added. ID: " + s.id);
    }

    static void viewStudents() {

        if (students.isEmpty()) {

            System.out.println(
                    "No students found.");

            return;
        }

        System.out.println(
                "ID | Name | Department");

        for (Student s : students) {

            System.out.println(
                    s.id + " | "
                    + s.name + " | "
                    + s.department);
        }
    }

    static void saveData() {

        FileManager.saveStudents(students);
        FileManager.saveExams(exams);

        System.out.println(
                "Data saved successfully.");
    }
}
