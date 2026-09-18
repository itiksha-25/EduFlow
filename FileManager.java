import java.io.*;
import java.util.ArrayList;

public class FileManager {

    static void saveStudents(
            ArrayList<Student> students) {

        try {
            FileWriter fw =
                    new FileWriter("attendance.txt");

            for (Student s : students) {

                fw.write(
                        s.id + "|" +
                        s.name + "|" +
                        s.department + "|" +
                        s.totalClasses + "|" +
                        s.attendedClasses + "\n");
            }

            fw.close();

        } catch (IOException e) {
            System.out.println("Error saving students.");
        }
    }

    static ArrayList<Student> loadStudents() {

        ArrayList<Student> students =
                new ArrayList<>();

        try {

            File file =
                    new File("attendance.txt");

            if (!file.exists())
                return students;

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(file));

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length == 5) {

                    students.add(
                            new Student(
                                    Integer.parseInt(data[0]),
                                    data[1],
                                    data[2],
                                    Integer.parseInt(data[3]),
                                    Integer.parseInt(data[4])
                            )
                    );
                }
            }

            br.close();

        } catch (Exception e) {

            System.out.println(
                    "Error loading student data.");
        }

        return students;
    }

    static void saveExams(
            ArrayList<Exam> exams) {

        try {

            FileWriter fw =
                    new FileWriter("exams.txt");

            for (Exam e : exams) {

                fw.write(
                        e.id + "|" +
                        e.subject + "|" +
                        e.date + "|" +
                        e.time + "|" +
                        e.room + "\n");
            }

            fw.close();

        } catch (IOException e) {

            System.out.println(
                    "Error saving exams.");
        }
    }

    static ArrayList<Exam> loadExams() {

        ArrayList<Exam> exams =
                new ArrayList<>();

        try {

            File file =
                    new File("exams.txt");

            if (!file.exists())
                return exams;

            BufferedReader br =
                    new BufferedReader(
                            new FileReader(file));

            String line;

            while ((line = br.readLine()) != null) {

                String[] data = line.split("\\|");

                if (data.length == 5) {

                    exams.add(
                            new Exam(
                                    Integer.parseInt(data[0]),
                                    data[1],
                                    data[2],
                                    data[3],
                                    data[4]
                            )
                    );
                }
            }

            br.close();

        } catch (Exception e) {

            System.out.println(
                    "Error loading exam data.");
        }

        return exams;
    }
}
