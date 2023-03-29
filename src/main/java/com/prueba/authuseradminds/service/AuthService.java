package com.prueba.authuseradminds.service;

import com.prueba.authuseradminds.helpers.ETypeAuthResult;
import com.prueba.authuseradminds.models.*;
import com.prueba.authuseradminds.payload.request.AuthRequest;
import com.prueba.authuseradminds.payload.request.LoginRequest;
import com.prueba.authuseradminds.payload.request.SignupRequest;
import com.prueba.authuseradminds.payload.response.JwtResponse;
import com.prueba.authuseradminds.payload.response.MessageResponse;
import com.prueba.authuseradminds.repository.*;
import com.prueba.authuseradminds.security.jwt.JwtUtils;
import com.prueba.authuseradminds.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.time.ZoneId;
import java.util.*;

/**
 * Servicio de integración con la capa de autenticación RCN SSO LDAP
 * @author David M.
 * @version 2023-03-27
 */
@Service
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;


    private final ZoneId timeZoneId = TimeZone.getTimeZone("America/Bogota").toZoneId();

    public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        Boolean successAuth = false;
        User user = userRepository.findByMobilePhone(loginRequest.getMobilePhone()).orElse(null);
        if (user != null) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

            if (userDetails.getActive().equals(userDetails.getActive(true))) {

                return ResponseEntity.ok(new JwtResponse(jwt,
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getFirstName(),
                        userDetails.getLastName(),
                        userDetails.getDateBirth(),
                        userDetails.getAddress(),
                        userDetails.getToken(),
                        userDetails.getMobilePhone(),
                        userDetails.getEmail(),
                        userDetails.getActive()
                ));
            }
            else {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("El usuario esta inactivo!"));
            }
        } else {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Credenciales inválidas"));
        }

    }

    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: El nombre de usuario ya esta en uso!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: El correo electrónico ya esta en uso!"));
        }

        if (signUpRequest.getPassword().equals("LDAP"))
            signUpRequest.setPassword(signUpRequest.getEmail());

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getDateBirth(),
                signUpRequest.getAddress(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getMobilePhone(),
                signUpRequest.getEmail(),
                signUpRequest.getActive());

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    public ResponseEntity<?> changeUserPassword(@Valid @RequestBody SignupRequest signUpRequest) {
        // Verifica que el usuario exista
        if (!userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username not found!"));
        }

        User user = userRepository.findByUsername(signUpRequest.getUsername()).orElse(null);
        if (user != null){
            String oldpassword = signUpRequest.getEmail();
            /* Se usa el campo de email para transportar el password anterior */
            if (encoder.matches(oldpassword, user.getPassword())){
                user.setPassword(encoder.encode(signUpRequest.getPassword()));
                userRepository.save(user);
            }
            else {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: The password does not correspond to the user"));
            }
        } else {
            return ResponseEntity.ok(new MessageResponse("Error: The user is not valid"));
        }

        return ResponseEntity.ok(new MessageResponse("User change password successfully!"));
    }


    public ResponseEntity<?> valideToken(@RequestHeader(name="Authorization") String token, HttpServletRequest request) {
        boolean result = jwtUtils.validateJwtToken(token.replaceAll("Bearer ", ""));
        HttpStatus httpStatus;
        if (result)
            httpStatus = HttpStatus.OK;
        else
            httpStatus = HttpStatus.UNAUTHORIZED;

        return new ResponseEntity<Boolean>(result, httpStatus);
    }


}
