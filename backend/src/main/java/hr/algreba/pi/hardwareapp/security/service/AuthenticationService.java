package hr.algreba.pi.hardwareapp.security.service;

import hr.algreba.pi.hardwareapp.security.dto.LoginDTO;
import hr.algreba.pi.hardwareapp.security.command.LoginCommand;

import java.util.Optional;

public interface AuthenticationService {

    Optional<LoginDTO> login(LoginCommand command);

}
