package hr.eestec_zg.cvdbbackend.config.security;

import hr.eestec_zg.cvdbbackend.domain.model.User;
import hr.eestec_zg.cvdbbackend.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DatabaseAuthenticationProvider implements AuthenticationProvider {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseAuthenticationProvider.class);

    private static final String CREDENTIALS_HIDDEN = "HIDDEN";

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public DatabaseAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = Objects.requireNonNull(userService);
        this.passwordEncoder = Objects.requireNonNull(passwordEncoder);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        return authenticate(username, password);
    }

    private Authentication authenticate(String username, String password) {
        if (logger.isDebugEnabled()) {
            logger.debug("Received authenticate request for user " + username);
        }

        Optional<User> userOptional = this.userService.findUserByUserName(username);

        if (!userOptional.isPresent()) {

            if (logger.isDebugEnabled()) {
                logger.debug("User {} not found in database", username);
            }

            throw new BadCredentialsException("Invalid username or password");
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {

            if (logger.isDebugEnabled()) {
                logger.debug("Password for user {} is incorrect", username);
            }

            throw new BadCredentialsException("Invalid username or password");
        }

        if (!user.getEnabled().equals(Boolean.TRUE)) {
            if (logger.isDebugEnabled()) {
                logger.debug("User {} is disabled", username);
            }

            throw new DisabledException("User acccount is disabled");
        }

        CvdbUserDetails userDetails = new CvdbUserDetails(user);
        List<GrantedAuthority> authorities = getGrantedAuthorities(user);

        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(userDetails, CREDENTIALS_HIDDEN, authorities);

        if(logger.isDebugEnabled()) {
            logger.debug("Resolved user details {} for user {}", userDetails, user);
        }

        return token;
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new LinkedList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
        logger.info("Authorities : {}", authorities);
        return authorities;
    }

    @Override
    public boolean supports(Class<?> authenticationClass) {
        return authenticationClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
