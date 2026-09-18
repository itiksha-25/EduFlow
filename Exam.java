import java.util.ArrayList;
import java.util.Scanner;

public class Exam {

    int id;
    String subject;
    String date;
    String time;
    String room;

    Exam(int id, String subject,
         String date, String time,
         String room) {

        this.id = id;
        this.subject = subject;
        this.date = date;
        this.time = time;
        this.room = room;
    }

    static void addExam(
            ArrayList<Exam> exams,
            Scanner sc) {

        System.out.print("Subject: ");
        String subject = sc.nextLine();

        System.out.print("Date (DD/MM/YYYY): ");
        String date = sc.nextLine();

        System.out.print("Time (HH:MM): ");
        String time = sc.nextLine();

        System.out.print("Room: ");
        String room = sc.nextLine();

        for (Exam e : exams) {

            if (e.date.equals(date)
                    && e.time.equals(time)
                    && e.room.equalsIgnoreCase(room)) {

                System.out.println(
                        "Conflict! Room already booked.");
                return;
            }
        }

        exams.add(new Exam(
                exams.size() + 1,
                subject,
                date,
                time,
                room));

        System.out.println("Exam added successfully.");
    }

    static void viewExams(
            ArrayList<Exam> exams) {

        if (exams.isEmpty()) {
            System.out.println("No exams scheduled.");
            return;
        }

        for (Exam e : exams) {

            System.out.println(
                    e.id + " | "
                    + e.subject + " | "
                    + e.date + " | "
                    + e.time + " | Room "
                    + e.room);
        }
    }
}
