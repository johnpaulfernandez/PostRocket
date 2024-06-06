package org.perscholas.capstone.postrocket.config;

import org.perscholas.capstone.postrocket.models.Role;
import org.perscholas.capstone.postrocket.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

// Implement UserDetails interface, which gives us more flexibility and control over user authorization and authentication processes.
// For instance, set a user account disabled/enabled and other.
public class UserPrincipal implements UserDetails {
    private User user;
    private List<Role> roles;

    public UserPrincipal(User user, List<Role> roles) {
        super();
        this.user = user;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
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
