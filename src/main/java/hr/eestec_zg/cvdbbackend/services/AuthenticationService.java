package hr.eestec_zg.cvdbbackend.services;

public interface AuthenticationService {
    boolean checkAuthentication(String username, String password);
}
