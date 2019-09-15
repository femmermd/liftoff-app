package org.launchcode.models.Objects;

import javax.persistence.*;

@Entity
public class Comment {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    User user;

    @ManyToOne
    @JoinColumn(name="review_id")
    Review review;

    private String text;

    public Comment(){};

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", review=" + review +
                ", text='" + text + '\'' +
                '}';
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

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
