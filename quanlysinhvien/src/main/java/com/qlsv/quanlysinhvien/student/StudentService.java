package com.qlsv.quanlysinhvien.student;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repo;

    public Page<Student> listAll(int pageNumber){
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, 5);
        return repo.findAll(pageRequest);
    }

    public Student findByID(Integer id){
        return repo.findById(id).get();
    }

    public void save(Student student){
        repo.save(student);
    }

    public void delete(Integer id){
        repo.deleteById(id);
    }
}
