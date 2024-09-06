package github.kauahaymon.auth_api.controllers;

import github.kauahaymon.auth_api.dtos.AuthPayload;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @PostMapping
    public String auth(@RequestBody AuthPayload authPayload) {
        return "token <<<";
    }
}
