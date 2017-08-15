package hr.eestec_zg.cvdbbackend.services.impl;

import hr.eestec_zg.cvdbbackend.domain.model.User;
import hr.eestec_zg.cvdbbackend.services.AuthenticationService;
import hr.eestec_zg.cvdbbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthenticationServiceImpl(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean checkAuthentication(String username, String password) {
        Optional<User> userOptional = this.userService.findUserByUserName(username);

        if (!userOptional.isPresent()) {
            return false;
        }

        User user = userOptional.get();

        return this.passwordEncoder.matches(password, user.getPassword());
    }
}
