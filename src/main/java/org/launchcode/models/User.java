package org.launchcode.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {

    @NotNull
    @Size(min=3,max=25)
    private String userName;

    @Id
    @GeneratedValue
    private int id;

    public User(){};

    public User(String userName){
        this();
        this.userName=userName;
    }

    



}
