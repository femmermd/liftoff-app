package org.launchcode.models.forms;

import org.launchcode.models.User;

import javax.validation.constraints.NotNull;


public class RegisterForm {

    private User user;

    @NotNull
    private int userId;

    public RegisterForm(){}

    public RegisterForm(User user){}


}
