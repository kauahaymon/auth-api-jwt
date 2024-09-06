package github.kauahaymon.auth_api.services.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import github.kauahaymon.auth_api.dtos.AuthPayload;
import github.kauahaymon.auth_api.entities.User;
import github.kauahaymon.auth_api.repositories.UserRepository;
import github.kauahaymon.auth_api.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return userRepository.findByLogin(login);
    }

    @Override
    public String getToken(AuthPayload authPayload) {
        User user = userRepository.findByLogin(authPayload.login());  
        return generateJwtToken(user);
    }

    public String generateJwtToken(User user) {

        try {
            Algorithm algorithm = Algorithm.HMAC256("my-secret");
            return JWT.create()
                    .withIssuer("auth-api")
                    .withSubject(user.getName())
                    .withExpiresAt(generateExpirationDate())
                    .sign(algorithm);

        } catch (JWTCreationException e) {
            throw new RuntimeException("Error while generating token" + e.getMessage());
        }
    }

    private Instant generateExpirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
