package fr.gatherthem.gatherthem_server.user.exposition.controller;

import fr.gatherthem.gatherthem_server.commons.Mail;
import fr.gatherthem.gatherthem_server.commons.Utils;
import fr.gatherthem.gatherthem_server.commons.exception.NotFoundException;
import fr.gatherthem.gatherthem_server.user.domain.AppUser;
import fr.gatherthem.gatherthem_server.user.domain.model.UserCredentials;
import fr.gatherthem.gatherthem_server.user.domain.model.UserModel;
import fr.gatherthem.gatherthem_server.user.domain.model.UserUpdateModel;
import fr.gatherthem.gatherthem_server.user.domain.service.UserService;
import fr.gatherthem.gatherthem_server.user.exception.CurrentPasswordIncorrectException;
import fr.gatherthem.gatherthem_server.user.exception.EmailAlreadyExistException;
import fr.gatherthem.gatherthem_server.user.exception.EmailHasNotBeenVerifiedException;
import fr.gatherthem.gatherthem_server.user.exception.UsernameAlreadyExistException;
import fr.gatherthem.gatherthem_server.user.exposition.dto.UserDto;
import fr.gatherthem.gatherthem_server.user.exposition.dto.UserRegisterDto;
import fr.gatherthem.gatherthem_server.user.exposition.dto.UserUpdateDto;
import fr.gatherthem.gatherthem_server.user.mapper.UserMapper;
import fr.gatherthem.gatherthem_server.user.mapper.UserRegisterMapper;
import fr.gatherthem.gatherthem_server.user.mapper.UserUpdateMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    private final Mail mail;

    public UserController(AuthenticationManager authenticationManager, UserService userService, Mail mail) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.mail = mail;
    }

    /**
     * Authenticates a user to the app
     * @param userCredentials the credentials used to log in
     * @return
     *   <p>200 if the user was successfully authenticated, with their information</p>
     *   <p>401 if the credentials are incorrect</p>
     *   <p>500 if an error occurred</p>
     */
    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserCredentials userCredentials, HttpServletRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredentials.getUsername(), userCredentials.getPassword(), new ArrayList<>()));
            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);

                AppUser connectedUser = (AppUser) authentication.getPrincipal();

                UserDto userDto = UserMapper.mapAppUserToUserDto(connectedUser);

                mail.sendMail(connectedUser.getEmail(), "Gatherthem - Connexion", "Bonjour " + connectedUser.getUsername() + ", une connexion à votre compte Gatherthem a été effectué depuis l'adresse " + Optional.ofNullable(request.getHeader("X-FORWARDED-FOR")).orElse(request.getRemoteAddr()) + ".");

                return ResponseEntity.ok().body(userDto);
            } else return ResponseEntity.status(401).build();
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Logouts an authenticated user
     * @return
     *   <p>200 if the user was successfully logged out</p>
     *   <p>403 if no user is authenticated</p>
     *   <p>500 if an error occurred</p>
     */
    @GetMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request, HttpServletResponse response) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if (auth != null) {
                new SecurityContextLogoutHandler().logout(request, response, auth);
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Creates a new user
     * @param userRegisterDto the user to create
     * @return
     *   <p>201 if the user was successfully created</p>
     *   <p>400 if the user to create is not valid</p>
     *   <p>409 if the username or email is already taken</p>
     */
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDto userRegisterDto) {
        if (userRegisterDto.getUsername() == null || !Utils.isValidEmail(userRegisterDto.getEmail()) || userRegisterDto.getPassword() == null || userRegisterDto.getCode() == null) {
            return ResponseEntity.badRequest().build();
        } else {
            try {
                userService.create(UserRegisterMapper.mapDtoToModel(userRegisterDto));
                return ResponseEntity.created(URI.create("/")).build();
            } catch (UsernameAlreadyExistException e) {
                return ResponseEntity.status(409).body("USERNAME_ALREADY_EXIST");
            } catch (EmailAlreadyExistException e) {
                return ResponseEntity.status(409).body("EMAIL_ALREADY_EXIST");
            } catch (EmailHasNotBeenVerifiedException e) {
                return ResponseEntity.status(409).body("EMAIL_HAS_NOT_BEEN_VERIFIED");
            }
        }
    }

    @PostMapping("/email/verify")
    public ResponseEntity<String> verifyEmail(@RequestBody String email) {
        if (Utils.isValidEmail(email)) {
            try {
                userService.verifyEmail(email);
                return ResponseEntity.ok().build();
            } catch (EmailAlreadyExistException e) {
                return ResponseEntity.status(409).body("EMAIL_ALREADY_EXIST");
            }
        } else return ResponseEntity.badRequest().build();
    }

    /**
     * Gets a user information
     * @return
     *   <p>200 if the user was authenticated, with their information</p>
     *   <p>403 if no user is authenticated</p>
     */
    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDto> getProfile() {
        AppUser connectedUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDto userDto = UserMapper.mapAppUserToUserDto(connectedUser);
        userDto.setNbCollections(userService.nbCollectionsByUserId(connectedUser.getId()));
        userDto.setNbItems(userService.nbItemsByUserId(connectedUser.getId()));

        return ResponseEntity.ok().body(userDto);
    }

    /**
     * Gives the premium authority to a user
     * @return
     *   <p>200 if the premium authority was successfully given to the user, with their information</p>
     *   <p>401 if an error occurred</p>
     *   <p>403 if no user is authenticated</p>
     *   <p>404 if the user doesn't exist</p>
     */
    @PutMapping("/premium")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDto> premium() {
        try{
            AppUser connectedUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserModel user = userService.premium(connectedUser.getId());
            UserDto userDto = UserMapper.mapUserModelToUserDto(user);
            return ResponseEntity.ok().body(userDto);
        }
        catch (NotFoundException e){
            return ResponseEntity.status(404).build();
        }
        catch (Exception e){
            return ResponseEntity.status(401).build();
        }
    }

    /**
     * Updates a user information
     * @param userUpdateDto the user to update
     * @return
     *   <p>200 if the user was successfully updated, with their information</p>
     *   <p>400 if the user to update is not valid</p>
     *   <p>401 if the current password is not right</p>
     *   <p>403 if no user is authenticated</p>
     *   <p>409 if the username or email is already taken</p>
     *   <p>500 if an error occurred</p>
     */
    @PutMapping("/update")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDto> updateProfile(@RequestBody UserUpdateDto userUpdateDto) {
        if (userUpdateDto.getUsername() == null || !Utils.isValidEmail(userUpdateDto.getEmail())
                || (userUpdateDto.getNewPassword() != null && !userUpdateDto.getNewPassword().equals(userUpdateDto.getNewPasswordConfirm()))
                || userUpdateDto.getPassword() == null) {
            return ResponseEntity.badRequest().build();
        } else {
            try {
                AppUser connectedUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                UserUpdateModel user = UserUpdateMapper.mapDtoToModel(userUpdateDto);
                UserModel res = userService.update(connectedUser.getId(), user);
                return ResponseEntity.ok(UserMapper.mapModelToDto(res));
            } catch (UsernameAlreadyExistException | EmailAlreadyExistException e) {
                return ResponseEntity.status(409).build();
            } catch (CurrentPasswordIncorrectException e) {
                return ResponseEntity.status(401).build();
            } catch (NotFoundException e) {
                return ResponseEntity.notFound().build();
            }
        }
    }
}
