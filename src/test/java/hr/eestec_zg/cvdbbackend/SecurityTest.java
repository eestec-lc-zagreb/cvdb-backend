package hr.eestec_zg.cvdbbackend;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SecurityTest {
    private static final Logger logger = LoggerFactory.getLogger(SecurityTest.class);

    @Test
    public void passwordEncoder() {
        logger.debug("{}", new BCryptPasswordEncoder(6).encode("password"));
    }

}
