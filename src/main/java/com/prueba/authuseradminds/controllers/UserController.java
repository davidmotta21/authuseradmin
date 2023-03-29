package com.prueba.authuseradminds.controllers;

import com.prueba.authuseradminds.models.*;
import com.prueba.authuseradminds.payload.request.SignupRequest;
import com.prueba.authuseradminds.payload.response.MessageResponse;
import com.prueba.authuseradminds.repository.UserRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * Controlador de la capa de servicios  para USER
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class UserController extends DataServiceController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;

    /**
     * Sobrecarga de metodo para consultar la lista de elementos USER
     *
     * @return
     */
    @GetMapping(serviceNameV1 + "/users/all")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> userList() {
        List<User> users = userRepository.findAll();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }


    /**
     * Metodo para consultar un usuario por id
     *
     * @return
     */
    @GetMapping(serviceNameV1 + "/user/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(new User());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Metodo para eliminar un usuario por id
     *
     * @return
     */
    @DeleteMapping(serviceNameV1 + "/user/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id).orElse(new User());
        userRepository.delete(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /**
     * Metodo para crear el objeto User asignando el usuario activo
     *
     * @param signupRequest Objeto User con la informaci�n requerida
     * @return
     */
    @PostMapping(serviceNameV1 + "/user")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> createUser(@Valid @RequestBody SignupRequest signupRequest) {
        User user;
        user = userRepository.findById(signupRequest.getId()).orElse(null);

        if (!signupRequest.getUsername().equals(user.getUsername())) {
            if (userRepository.existsByUsername(signupRequest.getUsername())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: El nombre de usuario ya está en uso!"));
            }
        }

        if (!signupRequest.getEmail().equals(user.getEmail())){
            if (userRepository.existsByEmail(signupRequest.getEmail())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: El correo electrónico ya esta en uso!"));
            }
        }

        user.setUsername(signupRequest.getUsername());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setEmail(signupRequest.getEmail());
        user.setActive(signupRequest.getActive());

        if (!signupRequest.getPassword().equals(user.getPassword())){
            user.setPassword(encoder.encode(signupRequest.getPassword()));
        }

        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);

    }

    /**
     * Metodo para actualizar el objeto User asignando el usuario activo
     *
     * @param signupRequest Objeto User con la informaci�n requerida
     * @return
     */
    @PutMapping(serviceNameV1 + "/user")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateUser(@Valid @RequestBody SignupRequest signupRequest) {
        User user;
        user = userRepository.findById(signupRequest.getId()).orElse(null);

        user.setUsername(signupRequest.getUsername());
        user.setFirstName(signupRequest.getFirstName());
        user.setLastName(signupRequest.getLastName());
        user.setEmail(signupRequest.getEmail());
        user.setActive(signupRequest.getActive());
        user.setPassword(encoder.encode(signupRequest.getPassword()));
        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}