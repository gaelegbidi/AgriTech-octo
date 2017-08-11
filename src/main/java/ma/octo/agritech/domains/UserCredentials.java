package ma.octo.agritech.domains;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class UserCredentials {
    private String username;
    private String password;

    public UserCredentials(String name, String password, List<GrantedAuthority> authorityList) {

    }

    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
