package hr.eestec_zg.cvdbbackend.repositories;

import hr.eestec_zg.cvdbbackend.TestBase;
import hr.eestec_zg.cvdbbackend.TestUtils;
import hr.eestec_zg.cvdbbackend.domain.model.User;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class UserRepositoryTest extends TestBase {

    @Test
    public void testFindingUserByCorrectUsername() {
        User testUser = TestUtils.sampleUserWithUsername(TestUtils.SAMPLE_USERNAME);
        userRepository.save(testUser);

        User foundUser = userRepository.findUserByUsername(TestUtils.SAMPLE_USERNAME);

        assertNotNull("User not found", foundUser);
    }

    @Test
    public void testFindingUserByWrongUsername() {
        User testUser = TestUtils.sampleUserWithUsername(TestUtils.SAMPLE_USERNAME);
        userRepository.save(testUser);

        User foundUser = userRepository.findUserByUsername("DummyUsername");

        assertNull("User found", foundUser);
    }

}
