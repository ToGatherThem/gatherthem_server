package fr.bryanprolong.gatherthem.gatherthem_server.user.exposition.controller;

import fr.bryanprolong.gatherthem.gatherthem_server.user.domain.model.UserCredentials;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class UserController {
    private final AuthenticationManager authenticationManager;

    public UserController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @PostMapping
    public ResponseEntity<Void> login(@RequestBody UserCredentials userCredentials) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userCredentials.getUsername(), userCredentials.getPassword(), new ArrayList<>()));
            if(authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                return ResponseEntity.ok().build();
            }
            else return ResponseEntity.status(401).build();
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }
}
