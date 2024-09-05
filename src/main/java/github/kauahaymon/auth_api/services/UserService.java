package github.kauahaymon.auth_api.services;

import github.kauahaymon.auth_api.dtos.UserPayload;

public interface UserService {
    public UserPayload salveUser(UserPayload payload);
}
