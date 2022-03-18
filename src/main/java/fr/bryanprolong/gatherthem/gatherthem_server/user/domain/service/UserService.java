package fr.bryanprolong.gatherthem.gatherthem_server.user.domain.service;

import fr.bryanprolong.gatherthem.gatherthem_server.commons.dao.UserDao;
import fr.bryanprolong.gatherthem.gatherthem_server.commons.entity.AuthorityEntity;
import fr.bryanprolong.gatherthem.gatherthem_server.commons.entity.UserEntity;
import fr.bryanprolong.gatherthem.gatherthem_server.user.domain.AppUser;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserDao userDao, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userDao.findByUsername(username);
        String stringAuthorities = String.join(
                ",",
                user.getAuthorities().stream()
                        .map(AuthorityEntity::getCode)
                        .toList()
        );

        return new AppUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                AuthorityUtils.commaSeparatedStringToAuthorityList(stringAuthorities)
        );
    }
}
