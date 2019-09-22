package org.launchcode.models.forms;

import javassist.bytecode.ByteArray;
import org.launchcode.models.Objects.User;

import javax.persistence.Lob;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.File;


public class RegisterForm {


    @NotNull
    @Size(min=3, max=25)
    private String username;

    @NotNull
    @Size(min=6, max=20)
    private String password;

    @NotNull
    private String verify;

    @NotNull
    @Email
    private String email;


    private String hometown;

    private String bio;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public RegisterForm(){}

    public RegisterForm(User user){}



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

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }


}
