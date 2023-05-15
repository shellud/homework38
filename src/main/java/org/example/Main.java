package org.example;

import org.example.domain.Student;
import org.example.repository.StudentMysqlRepository;
import org.example.repository.StudentRepository;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        StudentRepository studentRepository = new StudentMysqlRepository();

        Student student1 = Student.builder()
                .age(25)
                .name("Fedenko")
                .groupId(1)
                .build();
        studentRepository.save(student1);

        Student student = studentRepository.getStudentById(3);
        System.out.println(student);

        List<Student> students = studentRepository.findAll();
        System.out.println(students);
    }
}