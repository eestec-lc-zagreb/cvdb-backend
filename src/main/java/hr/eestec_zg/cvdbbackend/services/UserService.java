package hr.eestec_zg.cvdbbackend.services;

import hr.eestec_zg.cvdbbackend.domain.model.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByUserName(String username);
}
