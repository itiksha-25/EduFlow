public class Student {

    int id;
    String name;
    String department;
    int totalClasses;
    int attendedClasses;

    Student(int id, String name, String department) {
        this.id = id;
        this.name = name;
        this.department = department;
        totalClasses = 0;
        attendedClasses = 0;
    }

    Student(int id, String name, String department,
            int totalClasses, int attendedClasses) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.totalClasses = totalClasses;
        this.attendedClasses = attendedClasses;
    }

    double getAttendance() {
        if (totalClasses == 0)
            return 0;

        return (attendedClasses * 100.0) / totalClasses;
    }

    boolean isEligible() {
        return getAttendance() >= 75;
    }
}
