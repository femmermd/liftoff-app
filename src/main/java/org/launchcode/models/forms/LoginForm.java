package org.launchcode.models.forms;

import org.launchcode.models.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



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
