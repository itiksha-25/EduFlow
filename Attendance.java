import java.util.ArrayList;
import java.util.Scanner;

public class Attendance {

    static void updateAttendance(
            ArrayList<Student> students,
            Scanner sc) {

        System.out.print("Enter student ID: ");
        int id = Integer.parseInt(sc.nextLine());

        Student s = findStudent(students, id);

        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Total classes: ");
        int total = Integer.parseInt(sc.nextLine());

        System.out.print("Classes attended: ");
        int attended = Integer.parseInt(sc.nextLine());

        if (total < 0 || attended < 0 || attended > total) {
            System.out.println("Invalid attendance.");
            return;
        }

        s.totalClasses = total;
        s.attendedClasses = attended;

        System.out.printf(
                "Attendance: %.2f%%%n",
                s.getAttendance());

        if (s.isEligible())
            System.out.println("Eligible.");
        else
            System.out.println("Not eligible.");
    }

    static void viewAttendance(
            ArrayList<Student> students) {

        if (students.isEmpty()) {
            System.out.println("No students.");
            return;
        }

        for (Student s : students) {

            System.out.printf(
                    "ID: %d | %s | %.2f%% | %s%n",
                    s.id,
                    s.name,
                    s.getAttendance(),
                    s.isEligible()
                            ? "Eligible"
                            : "Not Eligible");
        }
    }

    static void whatIf(
            ArrayList<Student> students,
            Scanner sc) {

        System.out.print("Enter student ID: ");
        int id = Integer.parseInt(sc.nextLine());

        Student s = findStudent(students, id);

        if (s == null) {
            System.out.println("Student not found.");
            return;
        }

        int total = s.totalClasses;
        int attended = s.attendedClasses;

        if (total == 0) {
            System.out.println("No attendance data available.");
            return;
        }

        if (s.isEligible()) {
            System.out.println("Already above 75%.");
            return;
        }

        int required = 0;

        while ((attended + required) * 100.0
                / (total + required) < 75) {

            required++;
        }

        System.out.println(
                "Classes required to reach 75%: "
                        + required);
    }

    static Student findStudent(
            ArrayList<Student> students,
            int id) {

        for (Student s : students) {
            if (s.id == id)
                return s;
        }

        return null;
    }
}
