package org.launchcode.models.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Review {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private User user;

    private String photoId;

    private int imageScale = 400;

    @OneToMany(mappedBy = "review")
    private Set<Comment> comments;

    @NotNull
    @Size(min=3, max=30)
    private String coffeeName;

    private String drinkType;

    private String origin;

    private Double rating;

    private String brewDevice;

    private String roaster;

    private String cafe;

    private String flavors;

    private String roast;

    private String story;

    public Review(){};


    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", user=" + user +
                ", comments=" + comments +
                '}';
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }


    public String getPhotoId() {
        return photoId;
    }

    public int getImageScale() {
        return imageScale;
    }

    public void setImageScale(int imageScale) {
        this.imageScale = imageScale;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public String getDrinkType() {
        return drinkType;
    }

    public void setDrinkType(String drinkType) {
        this.drinkType = drinkType;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getBrewDevice() {
        return brewDevice;
    }

    public void setBrewDevice(String brewDevice) {
        this.brewDevice = brewDevice;
    }

    public String getRoaster() {
        return roaster;
    }

    public void setRoaster(String roaster) {
        this.roaster = roaster;
    }

    public String getCafe() {
        return cafe;
    }

    public void setCafe(String cafe) {
        this.cafe = cafe;
    }

    public String getFlavors() {
        return flavors;
    }

    public void setFlavors(String flavors) {
        this.flavors = flavors;
    }

    public String getRoast() {
        return roast;
    }

    public void setRoast(String roast) {
        this.roast = roast;
    }
}
