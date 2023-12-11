package com.quitpux.api.resources;


import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quitpux.api.dto.LoginDTO;
import com.quitpux.api.dto.RegisterDTO;
import com.quitpux.api.service.IKeycloakService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/users")
@PreAuthorize("hasRole('ADMIN_CLIENT')")
public class UsersResource {

    @Autowired
    private IKeycloakService keycloakService;


    @PreAuthorize("permitAll()")
    @PostMapping("/authenticate")
    public Mono<ResponseEntity<String>> loginUser(@RequestBody LoginDTO loginDTO) throws URISyntaxException {
    	return keycloakService.loginUser(loginDTO)
                .map(response -> ResponseEntity.ok(response))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).body("Authentication error"));

    }
    
    @PreAuthorize("permitAll()")
    @PostMapping("/register")
    public Mono<ResponseEntity<?>> registerUser(@RequestBody RegisterDTO registerDTO) throws URISyntaxException {
        String response = keycloakService.registerUser(registerDTO);
        return Mono.justOrEmpty(ResponseEntity.created(new URI("/users/register")).body(response));
    }
    
    @GetMapping("/list")
    public Mono<ResponseEntity<?>> findAllUsers(){
        return Mono.justOrEmpty(ResponseEntity.ok(keycloakService.findAllUsers()));
    }


    @GetMapping("/find/{username}")
    public Mono<ResponseEntity<?>> searchUserByUsername(@PathVariable String username){
         return Mono.justOrEmpty(ResponseEntity.ok(keycloakService.searchUserByUsername(username)));
    }

    @GetMapping("/count")
    public Mono<ResponseEntity<?>> count(){
         return Mono.justOrEmpty(ResponseEntity.ok(keycloakService.countUsers()));
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<?>> updateUser(@PathVariable String id, @RequestBody RegisterDTO registerDTO){
        keycloakService.updateUser(id, registerDTO);
        return Mono.justOrEmpty(ResponseEntity.ok("User updated successfully"));
    }


    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<?>> deleteUser(@PathVariable String id){
        keycloakService.deleteUser(id);
        return Mono.justOrEmpty(ResponseEntity.noContent().build());
    }
}
