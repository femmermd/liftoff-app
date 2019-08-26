package org.launchcode.models;


import javax.persistence.*;
import javax.validation.constraints.Email;
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

    @NotNull
    @Size(min=6, max = 20)
    private String password;

  /*  @ManyToMany
    private List<Review> reviews;*/

    @NotNull
    @Email
    private String email;

    private String hometown;

/*    @OneToOne
    private int photoId;*/


    public User(){};

    public User(String userName){
        this();
        this.userName=userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

/*    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }*/

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

/*    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }*/
}
