package hr.eestec_zg.cvdbbackend.config.security;

import hr.eestec_zg.cvdbbackend.domain.model.Role;
import hr.eestec_zg.cvdbbackend.domain.model.User;

public class CvdbUserDetails {

    private Integer id;
    private String username;
    private String name;
    private Role role;

    public CvdbUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.name = user.getName();
        this.role = user.getRole();
    }

    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return "CvdbUserDetails{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", role=" + role +
                '}';
    }
}
