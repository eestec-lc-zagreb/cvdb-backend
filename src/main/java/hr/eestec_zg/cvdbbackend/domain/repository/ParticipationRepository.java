package hr.eestec_zg.cvdbbackend.domain.repository;

import hr.eestec_zg.cvdbbackend.domain.model.Participation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipationRepository extends JpaRepository<Participation, Participation.ParticipationId> {
}
