package fr.mleduc.resource.api.response;

import fr.mleduc.panache.FederunUser;

public class UserResponse {
    private final String username;
    private final String token;

    public UserResponse(FederunUser existingUser) {
        this.username = existingUser.login;
        this.token = existingUser.token;
    }


    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }
}
