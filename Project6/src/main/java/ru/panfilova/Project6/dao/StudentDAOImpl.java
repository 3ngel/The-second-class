package ru.panfilova.Project6.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.panfilova.Project6.entity.Student;

import java.util.List;
@Slf4j
@Repository
public class StudentDAOImpl implements StudentDAO {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Student> getAllStudents() {
        try {
            Query query = entityManager.createQuery("from Student");
            List<Student> allStudents = query.getResultList();
            log.info("Retrieved all students: {}", allStudents);
            return allStudents;
        } catch (Exception e) {
            log.error("Error retrieving all students", e);
            throw e;
        }
    }

    @Override
    public Student saveStudent(Student student) {
        try {
            Student savedStudent = entityManager.merge(student);
            log.info("Saved student: {}", savedStudent);
            return savedStudent;
        } catch (Exception e) {
            log.error("Error saving student", e);
            throw e;
        }
    }

    @Override
    public Student getStudent(int id) {
        try {
            Student student = entityManager.find(Student.class, id);
            log.info("Retrieved student: {}", student);
            return student;
        } catch (Exception e) {
            log.error("Error retrieving student with id: {}", id, e);
            throw e;
        }
    }

    @Override
    public void deleteStudent(int id) {
        try {
            Query query = entityManager.createQuery("delete from Student where id = :studentId");
            query.setParameter("studentId", id);
            query.executeUpdate();
            log.info("Deleted student with id: {}", id);
        } catch (Exception e) {
            log.error("Error deleting student with id: {}", id, e);
            throw e;
        }
    }
}

