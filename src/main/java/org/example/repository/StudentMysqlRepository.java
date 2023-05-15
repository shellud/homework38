package org.example.repository;

import org.example.domain.Student;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentMysqlRepository implements StudentRepository{
    @Override
    public void save(Student student) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://robot-do-user-1968994-0.b.db.ondigitalocean.com:25060/shell", "doadmin", "AVNS_I6wlDKjGszZn1wvLr9t");
             PreparedStatement statement = connection.prepareStatement("INSERT INTO student (name, age) VALUES (?, ?)")) {
            //statement.setInt(1, student.getId());
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            //statement.setInt(3, student.getGroupId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentById(int id) {
        Student student = null;
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://robot-do-user-1968994-0.b.db.ondigitalocean.com:25060/shell", "doadmin", "AVNS_I6wlDKjGszZn1wvLr9t");
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM student WHERE id = ?")) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int studentId = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int age = resultSet.getInt("age");
                    int groupId = resultSet.getInt("group_id");
                    student = new Student(studentId, age, name, groupId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Override
    public List<Student> findAll() {
        List<Student> result = new ArrayList<>();
        // Open a connection
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://robot-do-user-1968994-0.b.db.ondigitalocean.com:25060/shell", "doadmin", "AVNS_I6wlDKjGszZn1wvLr9t");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM student")) {
            while (rs.next()) {
                Student student = Student.builder()
                        .id(rs.getInt("id"))
                        .age(rs.getInt("age"))
                        .groupId(rs.getInt("group_id"))
                        .name(rs.getString("name"))
                        .build();

                result.add(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
