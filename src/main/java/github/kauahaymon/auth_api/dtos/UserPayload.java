package github.kauahaymon.auth_api.dtos;

public record UserPayload(
        String name,
        String login,
        String password
) {
}
