package hr.eestec_zg.cvdbbackend.services.impl;

import hr.eestec_zg.cvdbbackend.domain.model.User;
import hr.eestec_zg.cvdbbackend.domain.repository.UserRepository;
import hr.eestec_zg.cvdbbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> findUserByUserName(String username) {
        System.out.println();
        return Optional.ofNullable(this.userRepository.findUserByUsername(username));
    }
}
