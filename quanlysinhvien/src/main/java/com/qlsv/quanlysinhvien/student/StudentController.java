package com.qlsv.quanlysinhvien.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class StudentController {
    @Autowired
    private StudentService service;

    @GetMapping("/students")
    public String list(Model model){
        return listByPage(model, 1);
    }

    @GetMapping("/students/page/{pageNumber}")
    public String listByPage(Model model, @PathVariable("pageNumber") int pageNumber){
        Page<Student> pages = service.listAll(pageNumber);
        List<Student> listStudents = pages.getContent();
        long totalItems = pages.getTotalElements();
        int totalPages = pages.getTotalPages();

        model.addAttribute("listStudents", listStudents);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", pageNumber);
        return "students";
    }

    @GetMapping("/students/new")
    public String newStudent(Model model){
        model.addAttribute("student", new Student());

        return "student_form";
    }

    @PostMapping("/students/save")
    public String save(Student student){
        service.save(student);
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String delete(@PathVariable Integer id){
        service.delete(id);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Student student = service.findByID(id);
        model.addAttribute("student", student);
        return "/student_form";
    }
}
