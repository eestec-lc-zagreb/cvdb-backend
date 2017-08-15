package hr.eestec_zg.cvdbbackend;

import hr.eestec_zg.cvdbbackend.domain.repository.EventRepository;
import hr.eestec_zg.cvdbbackend.domain.repository.ParticipationRepository;
import hr.eestec_zg.cvdbbackend.domain.repository.StudentRepository;
import hr.eestec_zg.cvdbbackend.domain.repository.SubscriptionRepository;
import hr.eestec_zg.cvdbbackend.domain.repository.UserRepository;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
@Transactional
public abstract class TestBase {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected StudentRepository studentRepository;

    @Autowired
    protected EventRepository eventRepository;

    @Autowired
    protected ParticipationRepository participationRepository;

    @Autowired
    protected SubscriptionRepository subscriptionRepository;

}
