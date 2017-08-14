package hr.eestec_zg.cvdbbackend.domain.repository;

import hr.eestec_zg.cvdbbackend.domain.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer> {
}
