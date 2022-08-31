package fr.gatherthem.gatherthem_server.user.domain.service;

import fr.gatherthem.gatherthem_server.commons.Mail;
import fr.gatherthem.gatherthem_server.commons.exception.NotFoundException;
import fr.gatherthem.gatherthem_server.user.domain.AppUser;
import fr.gatherthem.gatherthem_server.user.domain.model.*;
import fr.gatherthem.gatherthem_server.user.exception.CurrentPasswordIncorrectException;
import fr.gatherthem.gatherthem_server.user.exception.EmailAlreadyExistException;
import fr.gatherthem.gatherthem_server.user.exception.EmailHasNotBeenVerifiedException;
import fr.gatherthem.gatherthem_server.user.exception.UsernameAlreadyExistException;
import fr.gatherthem.gatherthem_server.user.infrastructure.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService implements UserDetailsService {
    static ArrayList<EmailVerifyModel> emailVerifyModels = new ArrayList<>();

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final Mail mail;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, Mail mail) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mail = mail;
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
                    userModel.getImage(),
                    userModel.getUsername(),
                    userModel.getPassword(),
                    userModel.getAuthorities()
            );
        } else throw new UsernameNotFoundException("");
    }

    public void create(UserRegister userRegister) throws UsernameAlreadyExistException, EmailAlreadyExistException, EmailHasNotBeenVerifiedException {
        if(userRepository.findByUsername(userRegister.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistException();
        } else if(userRepository.findByEmail(userRegister.getEmail()).isPresent()) {
            throw new EmailAlreadyExistException();
        } else if(emailVerifyModels.stream().noneMatch(emailVerifyModel -> emailVerifyModel.getEmail().equals(userRegister.getEmail()) && emailVerifyModel.getCode().equals(userRegister.getCode()))) {
            throw new EmailHasNotBeenVerifiedException();
        } else {
            userRepository.create(userRegister.getUsername(), userRegister.getEmail(), passwordEncoder.encode(userRegister.getPassword()));
            emailVerifyModels.removeIf(emailVerifyModel -> emailVerifyModel.getEmail().equals(userRegister.getEmail()));
            mail.sendMail(userRegister.getEmail(), "Bienvenue chez Gatherthem", "Votre compte a bien été créé");
        }
    }

    public void verifyEmail(String email) throws EmailAlreadyExistException {
        emailVerifyModels.removeIf(emailVerifyModel -> emailVerifyModel.getExpirationDate().compareTo(new Date()) < 0);
        if(userRepository.findByEmail(email).isPresent()) {
            throw new EmailAlreadyExistException();
        } else {
            EmailVerifyModel emailVerifyModel = emailVerifyModels.stream().filter(emailVerifyModel1 -> emailVerifyModel1.getEmail().equals(email)).findFirst().orElse(null);
            if(emailVerifyModel == null) {
                emailVerifyModel = new EmailVerifyModel(email);
                emailVerifyModels.add(emailVerifyModel);
            }
            mail.sendMail(email, "Vérification de votre adresse email", "Voici votre code de vérification : " + emailVerifyModel.getCode());
        }
    }

    public UserModel update(UUID id, UserUpdateModel user) throws UsernameAlreadyExistException, EmailAlreadyExistException, CurrentPasswordIncorrectException, NotFoundException {
        Optional<UserModel> optionalUserModel = userRepository.findById(id);
        if (optionalUserModel.isPresent()) {
            UserModel userModel = optionalUserModel.get();

            if (!userModel.getUsername().equals(user.getUsername()) && userRepository.findByUsername(user.getUsername()).isPresent()) {
                throw new UsernameAlreadyExistException();
            } else if (!userModel.getEmail().equals(user.getEmail()) && userRepository.findByEmail(user.getEmail()).isPresent()) {
                throw new EmailAlreadyExistException();
            } else if (!passwordEncoder.matches(user.getPassword(), userModel.getPassword())) {
                throw new CurrentPasswordIncorrectException();
            } else {
                userModel.setUsername(user.getUsername());
                userModel.setEmail(user.getEmail());
                userModel.setImage(user.getImage());
                if (!user.getNewPassword().isBlank()) {
                    userModel.setPassword(passwordEncoder.encode(user.getNewPassword()));
                }
                return userRepository.update(userModel);
            }
        }
        else throw new NotFoundException();
    }

    public int nbCollectionsByUserId(UUID userId) {
        return userRepository.nbCollectionsByUserId(userId);
    }

    public int nbItemsByUserId(UUID userId) {
        return userRepository.nbItemsByUserId(userId);
    }

    public UserModel premium(UUID id) throws NotFoundException {
       return userRepository.addAuthority(id, "PREMIUM");
    }
}
