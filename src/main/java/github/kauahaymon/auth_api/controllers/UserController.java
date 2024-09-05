package github.kauahaymon.auth_api.controllers;

import github.kauahaymon.auth_api.dtos.UserPayload;
import github.kauahaymon.auth_api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserPayload registerUser(@RequestBody UserPayload payload) {
        return userService.salveUser(payload);
    }

    @GetMapping
    public String welcome() {
        return "Welcome!";
    }

}
