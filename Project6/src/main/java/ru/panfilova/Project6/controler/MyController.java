package ru.panfilova.Project6.controler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.panfilova.Project6.entity.Student;
import ru.panfilova.Project6.service.StudentService;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("/api")
public class MyController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public List<Student> allStudents() {
        try {
            return studentService.getAllStudents();
        } catch (Exception e) {
            // Логирование ошибки и возврат ответа с кодом 500
            log.error("Error retrieving all students", e);
            throw e;
        }
    }

    @GetMapping("/students/{id}")
    public Student getStudent(@PathVariable("id") int id) {
        try {
            return studentService.getStudent(id);
        } catch (Exception e) {
            // Логирование ошибки и возврат ответа с кодом 500
            log.error("Error retrieving student with id: {}", id, e);
            throw e;
        }
    }

    @PostMapping("/students")
    public Student saveStudent(@RequestBody Student student) {
        try {
            return studentService.saveStudent(student);
        } catch (Exception e) {
            // Логирование ошибки и возврат ответа с кодом 500
            log.error("Error saving student: {}", student, e);
            throw e;
        }
    }

    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student student) {
        try {
            studentService.saveStudent(student);
            return student;
        } catch (Exception e) {
            // Логирование ошибки и возврат ответа с кодом 500
            log.error("Error updating student: {}", student, e);
            throw e;
        }
    }

    @DeleteMapping("/students/{id}")
    public void deleteStudent(@PathVariable("id") int id) {
        try {
            studentService.deleteStudent(id);
        } catch (Exception e) {
            // Логирование ошибки и возврат ответа с кодом 500
            log.error("Error deleting student with id: {}", id, e);
            throw e;
        }
    }
}
