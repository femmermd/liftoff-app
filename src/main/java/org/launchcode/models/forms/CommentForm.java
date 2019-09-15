package org.launchcode.models.forms;

import org.launchcode.models.Objects.Comment;
import org.launchcode.models.Objects.Review;
import org.launchcode.models.Objects.User;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CommentForm {

    @NotNull
    @Size(min=1, max=140)
    private String text;


    @ManyToOne
    private int reviewId;


    public CommentForm(){};

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getReviewId() {
        return reviewId;
    }

    public void setReviewId(int reviewId) {
        this.reviewId = reviewId;
    }
}