package hr.eestec_zg.cvdbbackend;

import hr.eestec_zg.cvdbbackend.domain.model.Role;
import hr.eestec_zg.cvdbbackend.domain.model.User;

import java.time.ZonedDateTime;

public class TestUtils {

    public static final String SAMPLE_NAME = "NAME";
    public static final String SAMPLE_PASSWORD = "PASSWORD";
    public static final String SAMPLE_USERNAME = "SAMPLE_USERNAME";

    public static User sampleUserWithUsername(String username) {
        return new User()
                .username(username)
                .name(SAMPLE_NAME)
                .password(SAMPLE_PASSWORD)
                .role(Role.COMPANY_USER)
                .enabled(true)
                .createdAt(ZonedDateTime.now())
                .modifiedAt(ZonedDateTime.now());
    }

}
