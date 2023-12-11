package com.quitpux.api.service;

import java.util.List;

import org.keycloak.representations.idm.UserRepresentation;

import com.quitpux.api.dto.LoginDTO;
import com.quitpux.api.dto.RegisterDTO;

import reactor.core.publisher.Mono;

public interface IKeycloakService {

	Mono<String> loginUser(LoginDTO loginDTO);
    List<UserRepresentation> findAllUsers();
    List<UserRepresentation> searchUserByUsername(String username);
    int countUsers();
    String registerUser(RegisterDTO registerDTO);
    void deleteUser(String id);
    void updateUser(String id, RegisterDTO userDTO);
}
