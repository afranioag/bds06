package com.devsuperior.movieflix.dto;

import com.devsuperior.movieflix.entities.Review;
import com.devsuperior.movieflix.entities.User;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ReviewDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;
    private String text;
    private Long movieId;
    private UserDTO user;
    public ReviewDTO() {
    }

    public ReviewDTO(Long id, String text, Long movieId, User user) {
        this.id = id;
        this.text = text;
        this.movieId = movieId;
        this.user = new UserDTO(user);
    }

    public ReviewDTO(Review review) {
        id = review.getId();
        text = review.getText();
        movieId = review.getMovie().getId();
        user = new UserDTO(review.getUser());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getMovieId() {
        return movieId;
    }

    public void setMovieId(Long movieId) {
        this.movieId = movieId;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}
