package fr.bryanprolong.gatherthem.gatherthem_server.user.exposition.controller;

import fr.bryanprolong.gatherthem.gatherthem_server.commons.Utils;
import fr.bryanprolong.gatherthem.gatherthem_server.user.domain.AppUser;
import fr.bryanprolong.gatherthem.gatherthem_server.user.domain.model.UserCredentials;
import fr.bryanprolong.gatherthem.gatherthem_server.user.domain.service.UserService;
import fr.bryanprolong.gatherthem.gatherthem_server.user.exception.EmailAlreadyExistException;
import fr.bryanprolong.gatherthem.gatherthem_server.user.exception.UsernameAlreadyExistException;
import fr.bryanprolong.gatherthem.gatherthem_server.user.exposition.dto.UserDto;
import fr.bryanprolong.gatherthem.gatherthem_server.user.exposition.dto.UserRegisterDto;
import fr.bryanprolong.gatherthem.gatherthem_server.user.mapper.UserRegisterMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

                UserDto userDto = new UserDto();

                userDto.setId(connectedUser.getId());
                userDto.setUsername(connectedUser.getUsername());
                userDto.setEmail(connectedUser.getEmail());

                return ResponseEntity.ok().body(userDto);
            } else return ResponseEntity.status(401).build();
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).build();
        } catch (Exception e) {
            e.printStackTrace();
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
}
