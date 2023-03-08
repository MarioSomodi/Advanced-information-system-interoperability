package hr.algreba.pi.hardwareapp.security.service;

import hr.algreba.pi.hardwareapp.security.domain.User;

public interface JwtService {

    boolean authenticate(String token);

    String createJwt(User user);

}
