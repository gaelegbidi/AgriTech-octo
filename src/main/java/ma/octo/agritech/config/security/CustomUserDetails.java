package ma.octo.agritech.config.security;

import ma.octo.agritech.domains.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private String username;
    private String password;
    private Collection< ? extends GrantedAuthority> authorities;

    public CustomUserDetails(User u) {
        this.username = u.getUsername();
        this.password = u.getPassword();
        this.authorities = getGrantedAuthoritiesByStringRoles(u.getRoles());;

    }

    private List<GrantedAuthority> getGrantedAuthoritiesByStringRoles(String strRoles) {
        List<String> roles = Arrays.asList(strRoles.split("\\|")); //decouper  la liste a partir de |

        List<GrantedAuthority> auths = new ArrayList<>();
        for(String role : roles){
            auths.add(new SimpleGrantedAuthority(role.toUpperCase()));
        }
        return auths;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
