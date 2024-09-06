package github.kauahaymon.auth_api.services;

import github.kauahaymon.auth_api.dtos.AuthPayload;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthenticationService extends UserDetailsService {
    public String getToken(AuthPayload authPayload);
}
