package ru.panfilova.Project8.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.panfilova.Project8.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>
{
}
