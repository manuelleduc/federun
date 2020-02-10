package fr.mleduc.resource.api.model;

import javax.validation.constraints.NotBlank;

@UniqueUser
public class User {

    @NotBlank(message = "Username may not be blank")
    public String username;

    @NotBlank(message = "Password may not be blank")
    public String password;
}
