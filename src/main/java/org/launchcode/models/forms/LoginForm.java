package org.launchcode.models.forms;

import org.launchcode.models.Objects.User;

import javax.validation.constraints.NotNull;


public class LoginForm {

    @NotNull
    private String username;

    @NotNull
    private String password;


    public LoginForm(){}

    public LoginForm(User user){}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
