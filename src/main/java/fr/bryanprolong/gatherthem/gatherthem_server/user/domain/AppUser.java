package fr.bryanprolong.gatherthem.gatherthem_server.user.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

public class AppUser extends User {
    private final UUID id;
    private final String email;

    public AppUser(UUID id, String email, String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.id = id;
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AppUser appUser = (AppUser) o;
        return Objects.equals(id, appUser.id) && Objects.equals(email, appUser.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, email);
    }
}
