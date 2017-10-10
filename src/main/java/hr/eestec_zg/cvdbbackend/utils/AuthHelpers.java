package hr.eestec_zg.cvdbbackend.utils;

import hr.eestec_zg.cvdbbackend.config.security.CvdbUserDetails;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuthHelpers {

    /**
     * If current user doesn't have the same ID, an exception will be thrown
     *
     * @param id resource id we want to check
     */
    public static void checkPrivileges(int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        CvdbUserDetails userDetails = (CvdbUserDetails) authentication.getPrincipal();

        if (userDetails.getId() != id) {
            throw new InsufficientAuthenticationException("User has no privileges for this resource");
        }
    }

}
