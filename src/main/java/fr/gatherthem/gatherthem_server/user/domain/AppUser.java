package fr.gatherthem.gatherthem_server.user.domain;

import fr.gatherthem.gatherthem_server.user.domain.model.Authority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AppUser extends User {
    private final UUID id;
    private final String email;
    private final byte[] image;
    private final List<Authority> authorityList;

    public AppUser(UUID id, String email, byte[] image, String username, String password, List<Authority> authorities) {
        super(
                username,
                password,
                AuthorityUtils.commaSeparatedStringToAuthorityList(
                        String.join(
                                ",",
                                authorities.stream()
                                        .map(Authority::getCode)
                                        .toList()
                        )
                )
        );

        this.id = id;
        this.email = email;
        this.image = image;
        this.authorityList = authorities;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public byte[] getImage() {
        return image;
    }

    public List<Authority> getAuthorityList() {
        return authorityList;
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
