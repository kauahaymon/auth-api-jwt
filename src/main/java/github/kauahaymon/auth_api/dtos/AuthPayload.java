package github.kauahaymon.auth_api.dtos;

public record AuthPayload(
        String login,
        String password
) {
}
