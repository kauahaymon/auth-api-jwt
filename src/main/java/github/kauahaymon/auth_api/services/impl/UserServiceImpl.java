package github.kauahaymon.auth_api.services.impl;

import github.kauahaymon.auth_api.dtos.UserPayload;
import github.kauahaymon.auth_api.entities.User;
import github.kauahaymon.auth_api.repositories.UserRepository;
import github.kauahaymon.auth_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserPayload salveUser(UserPayload payload) {
        User raw = userRepository.findByLogin(payload.login());
        if (raw != null) {
            throw new RuntimeException("User already exists!");
        }

        var passwordHash = passwordEncoder.encode(payload.password());
        User user = new User(
                payload.name(),
                payload.login(),
                passwordHash
        );
        userRepository.save(user);

        return new UserPayload(
                user.getName(),
                user.getLogin(),
                user.getPassword()
        );
    }
}
