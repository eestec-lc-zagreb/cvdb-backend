package hr.eestec_zg.cvdbbackend.domain.repository;

import hr.eestec_zg.cvdbbackend.domain.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
