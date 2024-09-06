package github.kauahaymon.auth_api.controllers;

import github.kauahaymon.auth_api.dtos.AuthPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    public String auth(@RequestBody AuthPayload authPayload) {

        var userAuthenticationToken = new UsernamePasswordAuthenticationToken(authPayload.login(), authPayload.password());
        authenticationManager.authenticate(userAuthenticationToken);

        return "token <<<";
    }
}
