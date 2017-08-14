package hr.eestec_zg.cvdbbackend.domain.repository;

import hr.eestec_zg.cvdbbackend.domain.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriptionRepository extends JpaRepository<Subscription, Subscription.SubscriptionId> {
}
