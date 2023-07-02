package com.qlsv.quanlysinhvien;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.qlsv.quanlysinhvien.student.Student;
import com.qlsv.quanlysinhvien.student.StudentRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class TestsStudentRepository {
    @Autowired
    private StudentRepository repo;

    @Test
    public void addOneStudent(){
        Student student = new Student( "Truong Xuan Giang", "Nghe An");
        repo.save(student);
    }

    @Test
    public void addManyStudents(){
        Student student1 = new Student( "Le Anh Tuan", "Nghe An");
        Student student2 = new Student( "Phan Hoang Nam", "Thanh Hoa");
        Student student3 = new Student( "Le Duc Linh", "Nghe An");
        List<Student> students = List.of(student1,student2,student3);
        repo.saveAll(students);
    }

    @Test
    public void listStudents(){
        List<Student> students = repo.findAll();
        System.out.println(students);
    }

    @Test
    public void deleteStudentById(){
        repo.deleteById(4);
    }
}
