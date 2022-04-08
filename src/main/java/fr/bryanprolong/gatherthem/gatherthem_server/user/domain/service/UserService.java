package fr.bryanprolong.gatherthem.gatherthem_server.user.domain.service;

import fr.bryanprolong.gatherthem.gatherthem_server.user.domain.AppUser;
import fr.bryanprolong.gatherthem.gatherthem_server.user.domain.model.Authority;
import fr.bryanprolong.gatherthem.gatherthem_server.user.domain.model.UserModel;
import fr.bryanprolong.gatherthem.gatherthem_server.user.domain.model.UserRegister;
import fr.bryanprolong.gatherthem.gatherthem_server.user.exception.EmailAlreadyExistException;
import fr.bryanprolong.gatherthem.gatherthem_server.user.exception.UsernameAlreadyExistException;
import fr.bryanprolong.gatherthem.gatherthem_server.user.infrastructure.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserModel> optionalUser = userRepository.findByUsername(username);
        if(optionalUser.isPresent()) {
            UserModel userModel = optionalUser.get();

            String stringAuthorities = String.join(
                    ",",
                    userModel.getAuthorities().stream()
                            .map(Authority::getCode)
                            .toList()
            );

            return new AppUser(
                    userModel.getId(),
                    userModel.getEmail(),
                    userModel.getUsername(),
                    userModel.getPassword(),
                    userModel.getAuthorities()
            );
        } else throw new UsernameNotFoundException("");
    }

    public void create(UserRegister userRegister) throws UsernameAlreadyExistException, EmailAlreadyExistException {
        if(userRepository.findByUsername(userRegister.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistException();
        } else if(userRepository.findByEmail(userRegister.getEmail()).isPresent()) {
            throw new EmailAlreadyExistException();
        } else {
            userRepository.create(userRegister.getUsername(), userRegister.getEmail(), passwordEncoder.encode(userRegister.getPassword()));
        }
    }
}
