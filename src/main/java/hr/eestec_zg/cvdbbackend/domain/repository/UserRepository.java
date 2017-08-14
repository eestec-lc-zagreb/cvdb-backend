package hr.eestec_zg.cvdbbackend.domain.repository;

import hr.eestec_zg.cvdbbackend.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
