package org.launchcode.models.Objects;


import org.apache.commons.io.IOUtils;
import org.apache.commons.io.IOUtils.toByteArray;
import org.hibernate.validator.constraints.UniqueElements;
import sun.nio.ch.IOUtil;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class User {

    @NotNull
    @Size(min=3,max=25)
    private String username;

    @Id
    @GeneratedValue
    private int id;

    @NotNull
    @Size(min=6, max = 20)
    private String password;

    @OneToMany
    private List<Review> reviews;

    private String email;

    private String hometown;

    @Lob
    @Column(name="photo", columnDefinition = "BLOB")
    private byte[] photo;


    public User(){};

    public User(String username, String password, String email){

        this();
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Review> getReviews() {
        return reviews;
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
