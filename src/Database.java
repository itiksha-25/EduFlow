import java.sql.*;
import java.util.ArrayList;

public class Database {

    static final String URL = "jdbc:sqlite:eduflow.db";

    public static void initializeDatabase() {

        String sql = "CREATE TABLE IF NOT EXISTS students (" +
                "id INTEGER PRIMARY KEY," +
                "name TEXT NOT NULL," +
                "department TEXT," +
                "attendance REAL DEFAULT 0," +
                "marks REAL DEFAULT 0," +
                "project TEXT," +
                "project_status TEXT)";

        try (Connection con = DriverManager.getConnection(URL);
             Statement st = con.createStatement()) {

            st.executeUpdate(sql);

        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
    }

    public static void saveStudent(Main.Student s) {

        String sql = "INSERT OR REPLACE INTO students " +
                "(id,name,department,attendance,marks,project,project_status) " +
                "VALUES (?,?,?,?,?,?,?)";

        try (Connection con = DriverManager.getConnection(URL);
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, s.id);
            ps.setString(2, s.name);
            ps.setString(3, s.department);
            ps.setDouble(4, s.attendance);
            ps.setDouble(5, s.marks);
            ps.setString(6, s.project);
            ps.setString(7, s.projectStatus);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Could not save student: " + e.getMessage());
        }
    }

    public static ArrayList<Main.Student> loadStudents() {

        ArrayList<Main.Student> list = new ArrayList<>();

        String sql = "SELECT * FROM students ORDER BY id";

        try (Connection con = DriverManager.getConnection(URL);
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                Main.Student s = new Main.Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("department")
                );

                s.attendance = rs.getDouble("attendance");
                s.marks = rs.getDouble("marks");
                s.project = rs.getString("project");
                s.projectStatus = rs.getString("project_status");

                list.add(s);
            }

        } catch (SQLException e) {
            System.out.println("Could not load students: " + e.getMessage());
        }

        return list;
    }
}
