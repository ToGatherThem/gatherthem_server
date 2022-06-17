package fr.gatherthem.gatherthem_server.user.exposition.controller;

import fr.gatherthem.gatherthem_server.commons.Utils;
import fr.gatherthem.gatherthem_server.commons.exception.NotFoundException;
import fr.gatherthem.gatherthem_server.user.domain.AppUser;
import fr.gatherthem.gatherthem_server.user.domain.model.UserCredentials;
import fr.gatherthem.gatherthem_server.user.domain.model.UserModel;
import fr.gatherthem.gatherthem_server.user.domain.model.UserUpdateModel;
import fr.gatherthem.gatherthem_server.user.domain.service.UserService;
import fr.gatherthem.gatherthem_server.user.exception.CurrentPasswordIncorrectException;
import fr.gatherthem.gatherthem_server.user.exception.EmailAlreadyExistException;
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

@RestController
@RequestMapping("/user")
public class UserController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public UserController(AuthenticationManager authenticationManager, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody UserCredentials userCredentials) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredentials.getUsername(), userCredentials.getPassword(), new ArrayList<>()));
            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);

                AppUser connectedUser = (AppUser) authentication.getPrincipal();

                UserDto userDto = UserMapper.mapAppUserToUserDto(connectedUser);

                return ResponseEntity.ok().body(userDto);
            } else return ResponseEntity.status(401).build();
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

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

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserRegisterDto userRegisterDto) {
        if (userRegisterDto.getUsername() == null || !Utils.isValidEmail(userRegisterDto.getEmail()) || userRegisterDto.getPassword() == null) {
            return ResponseEntity.badRequest().build();
        } else {
            try {
                userService.create(UserRegisterMapper.mapDtoToModel(userRegisterDto));
                return ResponseEntity.created(URI.create("/")).build();
            } catch (UsernameAlreadyExistException e) {
                return ResponseEntity.status(409).body("USERNAME_ALREADY_EXIST");
            } catch (EmailAlreadyExistException e) {
                return ResponseEntity.status(409).body("EMAIL_ALREADY_EXIST");
            }
        }
    }

    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<UserDto> getProfile() {
        AppUser connectedUser = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        UserDto userDto = UserMapper.mapAppUserToUserDto(connectedUser);
        userDto.setNbCollections(userService.nbCollectionsByUserId(connectedUser.getId()));
        userDto.setNbItems(userService.nbItemsByUserId(connectedUser.getId()));

        return ResponseEntity.ok().body(userDto);
    }

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
