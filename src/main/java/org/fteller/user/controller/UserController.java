package org.fteller.user.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.fteller.user.AppUser;
import org.fteller.user.repositories.ApplicationUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static org.fteller.Authorization.SecurityConstants.*;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private ApplicationUserRepository applicationUserRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;


    public UserController(ApplicationUserRepository applicationUserRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager) {
        this.applicationUserRepository = applicationUserRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/sign-up")
    public void signUp(@RequestBody AppUser user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        applicationUserRepository.save(user);
    }


    @PostMapping("/authenticate")
    public ResponseEntity<JWTToken> authorize(@Valid @RequestBody AppUser appUser) {
        AppUser user = applicationUserRepository.findByUsername(appUser.getUsername());
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(appUser.getUsername(), appUser.getPassword());

        Authentication authentication = this.authenticationManager.authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .claim("user_name",user.getUsername())
                .claim("role",user.getRole())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET.getBytes())
                .compact();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HEADER_STRING,TOKEN_PREFIX+token);
        return new ResponseEntity<>(new JWTToken(token), httpHeaders, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<String> getAllUser(){
        return applicationUserRepository.getAllUsername();
    }

//    @GetMapping("/all")
//    public List<String> getAllUser(){
//
//              return  applicationUserRepository.findAll()
//                       .stream()
//                      .map(e -> e.getUsername())
//                      .collect(Collectors.toList());
//    }


    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
}
